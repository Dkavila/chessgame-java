package me.dkavila.chess.entities;

import me.dkavila.board.entities.Board;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
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
}
