package cleancode.minesweeper.tobe.position;

import java.util.Objects;

public class CellPosition {
    private final int rowIndex;
    private final int columnIndex;

    public CellPosition(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
        }
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static CellPosition of(int rowIndex, int columnIndex) {
        return new CellPosition(
                rowIndex,
                columnIndex
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition cellPosition = (CellPosition) o;
        return rowIndex == cellPosition.rowIndex && columnIndex == cellPosition.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }

    public boolean isRowIndexGreaterThanOrEqual(int rowSize) {
        return this.rowIndex >= rowSize;
    }

    public boolean isColIndexGreaterThanOrEqual(int columnSize) {
        return this.columnIndex >= columnSize;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean canCalculatePositionBy(RelativePosition relativePosition) {
        return getRowIndex() + relativePosition.getDeltaRow() >= 0
                && getColumnIndex() + relativePosition.getDeltaCol() >= 0;
    }

    public boolean isRowIndexLessThan(int rowIndex) {
        return this.rowIndex < rowIndex;
    }

    public boolean isColIndexLessThan(int columnIndex) {
        return this.columnIndex < columnIndex;
    }

    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
        if (cannotCalculatePositionBy(relativePosition)) throw new IllegalArgumentException("움직일 수 없는 좌표입니다.");
        return CellPosition.of(
                this.rowIndex + relativePosition.getDeltaRow(),
                this.columnIndex + relativePosition.getDeltaCol()
        );
    }

    private boolean cannotCalculatePositionBy(RelativePosition relativePosition) {
        return !canCalculatePositionBy(relativePosition);
    }
}
