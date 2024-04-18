package me.dkavila.chess.entities.pieces;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves =  new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position position = new Position(0, 0);

        // Above the piece
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn());
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Below the piece
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn());
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Left the piece
        position.setValues(getPosition().getRow(), getPosition().getColumn() - 1);
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Right the piece
        position.setValues(getPosition().getRow(), getPosition().getColumn() + 1);
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Up-Left diagonal
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn() - 1);
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Up-right diagonal
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn() + 1);
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Down-left diagonal
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn() - 1);
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Down-right diagonal
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn() + 1);
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        return possibleMoves;
    }
}
