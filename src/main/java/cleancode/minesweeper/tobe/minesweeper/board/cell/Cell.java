package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell {
    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

    boolean isLandMine();

    void flag();

    void unflag();

    void open();

    boolean isChecked();

    boolean isOpened();
}
