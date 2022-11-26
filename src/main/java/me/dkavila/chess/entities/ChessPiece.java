package me.dkavila.chess.entities;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Piece;

public abstract class ChessPiece extends Piece {
    private final Color color;
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
