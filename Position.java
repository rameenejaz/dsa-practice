public class Position {
    int row=-1;
    int col=-1;
    Position(int r, int c) {
        this.row=r;
        this.col=c;
    }
    public String toString() {
        return "( " + row + "," + col + ")";
    }
}
