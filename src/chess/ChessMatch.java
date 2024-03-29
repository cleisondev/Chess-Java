package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private Board board;
    private Color currentplayer;
    private int turn;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();


    public ChessMatch(){
        board = new Board(8,8);//board recebe um objeto do valor Board com 8
        currentplayer = Color.WHITE;
        turn = 1;
        initialSetup();
        }
//Funcao que retorna uma matriz de peças
    public ChessPiece[][] getPieces(){
        //Vai retornar as rows e cols do proprio board que foi 8 no comeco
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        //
        for(int i=0;i< board.getRows();i++){
            for(int j =0;j < board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return  mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position pos = sourcePosition.toPosition();
        validateSourcePos(pos);
        return board.piece(pos).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos){
        Position source = sourcePos.toPosition();
        Position target = targetPos.toPosition();
        validateSourcePos(source);
        validateTargetPosition(source,target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private void  validateSourcePos(Position pos){
        if(!board.thereIsaPiece(pos)){
            throw new ChessException("There is no piece on source position");
        }
        if (currentplayer != ((ChessPiece)board.piece(pos)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if(!board.piece(pos).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible move to this piece, press Enter to continue.");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece cannot move to target position");
        }
    }

    private Piece makeMove(Position    source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p,target);
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return  capturedPiece;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }
    //Colocando as peças
    private void initialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

    private void nextTurn(){
        turn++;
        currentplayer  = (currentplayer == Color.WHITE) ? Color.BLACK : Color.WHITE;

    }

    public Color getCurrentplayer() {
        return currentplayer;
    }

    public int getTurn() {
        return turn;
    }
}

