package mazePath;

public class PathCoordinate {
    private final int row;
    private final int column;
    private final PathCoordinate parent;

    PathCoordinate(int row, int column) {
        this.row = row;
        this.column = column;
        parent = null;
    }

    PathCoordinate(int row, int column, PathCoordinate parent) {
        this.row = row;
        this.column = column;
        this.parent = parent;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public PathCoordinate getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return String.format("%d, %d", row, column);
    }
}
