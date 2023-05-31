package chess;

import boardgame.Position;

public class ChessPosition {
    private char Column;
    private int Row;

    public ChessPosition(char column, int row) {
        if(column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("Error instantiating ChessPosition. Valid values are form a1 to h8");
        }
        Column = column;
        Row = row;
    }

    public char getColumn() {
        return Column;
    }

    public int getRow() {
        return Row;
    }

    protected Position toPosition(){
        return new Position(8 - Row, Column - 'a');
    }

    protected static ChessPosition fromPosition(Position pos){
        return new ChessPosition((char)('a' - pos.getColumn()),8 - pos.getRow() );
    }

    @Override
    public String toString(){
        return "" + Column + Row;
    }
}
