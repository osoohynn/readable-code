package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.AppException;
import cleancode.minesweeper.tobe.GameBoard;

public interface OutputHandler {
    void showGameStartComments();

    void showBoard(GameBoard board);

    void showGameWinningComment();

    void showGameLosingComment();

    void showCommentForSelectingCell();

    void showCommentForUserAction();

    void showExceptionMessage(AppException e);

    void showSimpleMessage(String message);
}
