import javax.swing.JButton;


public class TicTacToeSquare extends JButton {
    private int column;
    private int row;

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}