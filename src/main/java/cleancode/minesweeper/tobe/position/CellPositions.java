package cleancode.minesweeper.tobe.position;

import cleancode.minesweeper.tobe.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CellPositions {
    private final List<CellPosition> cellPositions;

    private CellPositions(List<CellPosition> cellPositions) {
        this.cellPositions = cellPositions;
    }

    public static CellPositions of(List<CellPosition> cellPositions) {
        return new CellPositions(cellPositions);
    }

    public static CellPositions from(Cell[][] cells) {
        List<CellPosition> cellPositions = new ArrayList<>();

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cellPositions.add(CellPosition.of(row, col));
            }
        }

        return of(cellPositions);
    }

    public List<CellPosition> extractRandomPositions(int count) {
        List<CellPosition> positions = new ArrayList<>(cellPositions);

        Collections.shuffle(positions);
        return positions.subList(0, count);
    }

    public List<CellPosition> subtract(List<CellPosition> positionListToSubtract) {
        List<CellPosition> newCellPositions = new ArrayList<>(cellPositions);
        CellPositions positionsToSubtract = CellPositions.of(positionListToSubtract);

        return newCellPositions.stream().
                filter(positionsToSubtract::doesNotContain)
                .toList();
    }

    public List<CellPosition> getAllPositions() {
        return new ArrayList<>(cellPositions);
    }

    private boolean doesNotContain(CellPosition position) {
        return !cellPositions.contains(position);
    }
}
