package me.dkavila.chess.entities.pieces;

import me.dkavila.board.entities.Board;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }
}
