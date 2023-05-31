package boardgame;



public class Board {
    private  int Rows;
    private int Columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) throws BoardException {
        if(rows < 1 || columns < 1){
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        Rows = rows;
        Columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return Rows;
    }
    public int getColumns() {
        return Columns;
    }
    public Piece piece(int row, int column){
        if(!positionExists(row,column)){
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position pos){
        if(thereIsaPiece(pos)){
            throw new BoardException("There is already a pience on position" + pos);
        }
        pieces[pos.getRow()][pos.getColumn()] = piece;
        piece.position = pos;
    }

    private boolean positionExists(int row,int column ){
       return row >= 0 && row <= Rows && column >= 0 && column <= Columns;
    }

    public boolean positionExists(Position pos){
        return positionExists(pos.getRow(), pos.getColumn());
    }

    public  boolean thereIsaPiece(Position pos){
        return piece(pos) != null;
    }



}
