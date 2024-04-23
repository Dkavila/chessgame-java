package me.dkavila.chess.entities;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Piece;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.pieces.*;
import me.dkavila.chess.exception.ChessException;

public class ChessMatch {

    private int turn;

    private Color currentPlayer;

    private List<ChessPiece> whiteCaptured;

    private List<ChessPiece> blackCaptured;

    private ChessPiece enPassantVulnerable;

    private ChessPiece promoted;

    private final Board board;

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        whiteCaptured = new ArrayList<>();
        blackCaptured = new ArrayList<>();
        enPassantVulnerable = null;
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

    public ChessPiece getPromoted() {
        return promoted;
    }

    public void addWhiteCaptured(ChessPiece chessPiece){
        whiteCaptured.add(chessPiece);
    }

    public void addBlackCaptured(ChessPiece chessPiece){
        blackCaptured.add(chessPiece);
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
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

        promoted = null;

        ChessPiece movedPiece = (ChessPiece)board.getPiece(source);

        ChessPiece capturedPiece = (ChessPiece)board.makeMove(source, target);

        if(movedPiece instanceof Pawn){
            // Special Move - En Passant (remove captured piece)
            // If the pawn moved two steps, is vulnerable to en passant
            if((target.getRow() == (source.getRow() - 2) || target.getRow() == (source.getRow() + 2))){
                enPassantVulnerable = movedPiece;
            } else {
                enPassantVulnerable = null;
            }

            // If the Pawn changes the column and does not capture any piece
            if(source.getColumn() != target.getColumn() && capturedPiece == null){
                int direction = movedPiece.getColor() == Color.WHITE ? 1 : -1;
                capturedPiece = (ChessPiece)board.getPiece(target.getRow() + direction, target.getColumn());
                board.removePiece(capturedPiece.getPosition());
            }

            // Special Move - Promotion
            if((target.getRow() == 0 && movedPiece.getColor() == Color.WHITE) || (target.getRow() == 7 && movedPiece.getColor() == Color.BLACK)){
                promoted = movedPiece;
            }
        }

        // Special Move - Castling
        if(movedPiece instanceof King) {
            // Kingside Castling
            if(target.getColumn() == source.getColumn() + 2){
                Position sourceRookPosition = new Position(source.getRow(), source.getColumn() + 3);
                Position targetRookPosition = new Position(source.getRow(), source.getColumn() + 1);
                ChessPiece rook = (ChessPiece) board.getPiece(sourceRookPosition);
                board.removePiece(sourceRookPosition);
                board.placePiece(rook, targetRookPosition);
            }
            
            // Queenside Castling
            if(target.getColumn() == source.getColumn() - 2){
                Position sourceRookPosition = new Position(source.getRow(), source.getColumn() - 4);
                Position targetRookPosition = new Position(source.getRow(), source.getColumn() - 1);
                ChessPiece rook = (ChessPiece) board.getPiece(sourceRookPosition);
                board.removePiece(sourceRookPosition);
                board.placePiece(rook, targetRookPosition);
            }
        }
        

        if(capturedPiece != null){
            if (capturedPiece.getColor() == Color.WHITE) {
                whiteCaptured.add(capturedPiece);
            } else {
                blackCaptured.add(capturedPiece);
            }
        }

        nextTurn();

        return capturedPiece;
    }

    public ChessPiece replacePromotedPiece(String type){
        if(promoted == null){
            throw new IllegalStateException("There is no piece to be promoted");
        }
        if(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")){
            throw new InvalidParameterException("Invalid type for promotion");
        }
        Position position = promoted.getPosition();
        Piece piece = board.removePiece(position);
        ChessPiece newPiece = newPiece(type, promoted.getColor());
        board.placePiece(newPiece, position);

        return newPiece;
    }

    private ChessPiece newPiece(String type, Color color){
        switch(type) {
            case "B": return new Bishop(board, color);
            case "N": return new Knight(board, color);
            case "R": return new Rook(board, color);
            default: return new Queen(board, color);
        }
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
        // Placing White Pieces
        placeChessPiece('a', 1, new Rook(board, Color.WHITE));
        placeChessPiece('b', 1, new Knight(board, Color.WHITE));
        placeChessPiece('c', 1, new Bishop(board, Color.WHITE));
        placeChessPiece('d', 1, new Queen(board, Color.WHITE));
        placeChessPiece('e', 1, new King(board, Color.WHITE));
        placeChessPiece('f', 1, new Bishop(board, Color.WHITE));
        placeChessPiece('g', 1, new Knight(board, Color.WHITE));
        placeChessPiece('h', 1, new Rook(board, Color.WHITE));
        for( char column = 'a'; column <= 'h'; column++){
            placeChessPiece(column, 2, new Pawn(board, Color.WHITE, this));
        }

        // Placing Black Pieces
        placeChessPiece('a', 8, new Rook(board, Color.BLACK));
        placeChessPiece('b', 8, new Knight(board, Color.BLACK));
        placeChessPiece('c', 8, new Bishop(board, Color.BLACK));
        placeChessPiece('d', 8, new Queen(board, Color.BLACK));
        placeChessPiece('e', 8, new King(board, Color.BLACK));
        placeChessPiece('f', 8, new Bishop(board, Color.BLACK));
        placeChessPiece('g', 8, new Knight(board, Color.BLACK));
        placeChessPiece('h', 8, new Rook(board, Color.BLACK));
        for( char column = 'a'; column <= 'h'; column++){
            placeChessPiece(column, 7, new Pawn(board, Color.BLACK, this));
        }
    }
}
