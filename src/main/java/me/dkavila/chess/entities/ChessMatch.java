package me.dkavila.chess.entities;

import java.util.ArrayList;
import java.util.List;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.pieces.*;
import me.dkavila.chess.exception.ChessException;

public class ChessMatch {

    private int turn;

    private Color currentPlayer;

    private List<ChessPiece> whiteCaptured = new ArrayList<>();

    private List<ChessPiece> blackCaptured = new ArrayList<>();

    private final Board board;

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public List<ChessPiece> getBlackCaptured() {
        return blackCaptured;
    }

    public List<ChessPiece> getWhiteCaptured() {
        return whiteCaptured;
    }

    public void addWhiteCaptured(ChessPiece chessPiece){
        whiteCaptured.add(chessPiece);
    }

    public void addBlackCaptured(ChessPiece chessPiece){
        blackCaptured.add(chessPiece);
    }

    public ChessPiece[][] getChessPieces() {
        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
        for(int row = 0; row < board.getRows(); row++){
            for(int column = 0; column < board.getColumns(); column++){
                chessPieces[row][column] = (ChessPiece) board.getPiece(row, column);
            }
        }
        return chessPieces;
    }

    public ChessPiece moveChessPiece(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        nextTurn();
        return (ChessPiece)board.makeMove(source, target);
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            ChessException.invalidSourcePosition(ChessPosition.fromPosition(position));
        }
        if(currentPlayer != ((ChessPiece)board.getPiece(position)).getColor()){
            ChessException.opponentPieceSelected(currentPlayer);
        }
        if(!board.getPiece(position).isThereAnyPossibleMove()){
            ChessException.noPossibleMoves();
        }
    }

    private void validateTargetPosition(Position sourcePosition, Position targetPosition){
        if(!board.getPiece(sourcePosition).possibleMove(targetPosition)){
            ChessException.invalidMove();
        }
    }

    public boolean[][] possibleMoves(ChessPosition chessPosition){
        Position position = chessPosition.toPosition();
        validateSourcePosition(position);
        return board.getPiece(position).possibleMoves();
    }

    private void placeChessPiece(char column, int row, ChessPiece chessPiece){
        board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void initialSetup(){
        //  Placing White Pieces    //
        placeChessPiece('a', 1, new Rook(board, Color.WHITE));
        placeChessPiece('b', 1, new Horse(board, Color.WHITE));
        placeChessPiece('c', 1, new Bishop(board, Color.WHITE));
        placeChessPiece('d', 1, new Queen(board, Color.WHITE));
        placeChessPiece('e', 1, new King(board, Color.WHITE));
        placeChessPiece('f', 1, new Bishop(board, Color.WHITE));
        placeChessPiece('g', 1, new Horse(board, Color.WHITE));
        placeChessPiece('h', 1, new Rook(board, Color.WHITE));
        for( char column = 'a'; column <= 'h'; column++){
            placeChessPiece(column, 2, new Pawn(board, Color.WHITE));
        }

        //  Placing Black Pieces    //
        placeChessPiece('a', 8, new Rook(board, Color.BLACK));
        placeChessPiece('b', 8, new Horse(board, Color.BLACK));
        placeChessPiece('c', 8, new Bishop(board, Color.BLACK));
        placeChessPiece('d', 8, new Queen(board, Color.BLACK));
        placeChessPiece('e', 8, new King(board, Color.BLACK));
        placeChessPiece('f', 8, new Bishop(board, Color.BLACK));
        placeChessPiece('g', 8, new Horse(board, Color.BLACK));
        placeChessPiece('h', 8, new Rook(board, Color.BLACK));
        for( char column = 'a'; column <= 'h'; column++){
            placeChessPiece(column, 7, new Pawn(board, Color.BLACK));
        }
    }
}
