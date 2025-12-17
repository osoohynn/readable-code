package cleancode.minesweeper.tobe.minesweeper;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.exception.AppException;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

public class MineSweeper implements GameRunnable, GameInitializable {
    private final GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public MineSweeper(GameConfig gameConfig) {
        gameBoard = new GameBoard(gameConfig.getGameLevel());
        this.inputHandler = gameConfig.getInputHandler();
        this.outputHandler = gameConfig.getOutputHandler();
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    public void run() {
        outputHandler.showGameStartComments();

        while (gameBoard.isInProgress()) {
            try {
                outputHandler.showBoard(gameBoard);

                CellPosition cellPosition = getCellInputFromUser();
                UserAction userActionInput = getUserActionInputFromUser();
                actOnCell(cellPosition, userActionInput);

            } catch (AppException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.showSimpleMessage("예상치 못한 문제가 발생했습니다.");
            }
        }

        outputHandler.showBoard(gameBoard);

        if (gameBoard.isWonStatus()) {
            outputHandler.showGameWinningComment();
        }
        if (gameBoard.isLostStatus()) {
            outputHandler.showGameLosingComment();
        }
    }

    private CellPosition getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new AppException("잘못된 좌표를 선택하셨습니다.");
        }
        return cellPosition;
    }

    private UserAction getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    private void actOnCell(CellPosition cellPosition, UserAction userAction) {
        if (doesUserChooseToPlantFlag(userAction)) {
            gameBoard.flagAt(cellPosition);
            return;
        }

        if (doesUserChooseToOpenCell(userAction)) {
            gameBoard.openAt(cellPosition);
            return;
        }

        throw new AppException("잘못된 번호를 선택하셨습니다.");
    }

    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;
    }

    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }
}
