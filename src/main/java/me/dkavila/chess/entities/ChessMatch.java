package me.dkavila.chess.entities;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.pieces.*;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
        initialSetup();
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

    private void placeChessPiece(char column, int row, ChessPiece chessPiece){
        board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
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

        //  Placing Black Pieces    //
        placeChessPiece('a', 8, new Rook(board, Color.BLACK));
        placeChessPiece('b', 8, new Horse(board, Color.BLACK));
        placeChessPiece('c', 8, new Bishop(board, Color.BLACK));
        placeChessPiece('d', 8, new Queen(board, Color.BLACK));
        placeChessPiece('e', 8, new King(board, Color.BLACK));
        placeChessPiece('f', 8, new Bishop(board, Color.BLACK));
        placeChessPiece('g', 8, new Horse(board, Color.BLACK));
        placeChessPiece('h', 8, new Rook(board, Color.BLACK));
    }
}
