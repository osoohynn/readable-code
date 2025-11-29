package cleancode.minesweeper.tobe;

import java.util.Random;

public class GameBoard {
    private static final int LAND_MINE_COUNT = 10;
    private final Cell[][] board;

    public GameBoard(int rowSize, int colSize) {
        board = new Cell[rowSize][colSize];
    }

    private void initializeGame() {
        int rowSize = board.length;
        int colSize = board[0].length;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = Cell.create();
            }
        }

        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int col = new Random().nextInt(colSize);
            int row = new Random().nextInt(rowSize);
            board[row][col].turnOnLandMine();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(row, col)) {
                    continue;
                }
                int count = countNearByLandMines(row, col);
                board[row][col].updateNearByLandMineCount(count);
            }
        }
    }

    private int countNearByLandMines(int row, int col) {
        int rowSize = board.length;
        int colSize = board[0].length;

        int count = 0;

        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < colSize && isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < colSize && isLandMineCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMineCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < rowSize && col + 1 < colSize && isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

    private boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        return board[selectedRowIndex][selectedColIndex].isLandMine();
    }

}
