package boardgame;

public class Board {
    private  int Rows;
    private int Columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        Rows = rows;
        Columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return Rows;
    }

    public void setRows(int rows) {
        Rows = rows;
    }

    public int getColumns() {
        return Columns;
    }

    public void setColumns(int columns) {
        Columns = columns;
    }

    public Piece piece(int row, int column){
        return pieces[row][column];
    }

    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }


}
