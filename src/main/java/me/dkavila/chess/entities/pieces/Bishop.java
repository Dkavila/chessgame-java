package me.dkavila.chess.entities.pieces;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position position = new Position(0, 0);

        // Up-right diagonal
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn() - 1);
        while(isFreeMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
            position.setValues(position.getRow() - 1, position.getColumn() - 1);
        }
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Down-right diagonal
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn() + 1);
        while(isFreeMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
            position.setValues(position.getRow() - 1, position.getColumn() + 1);
        }
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Down-left diagonal
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn() - 1);
        while(isFreeMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
            position.setValues(position.getRow() + 1, position.getColumn() - 1);
        }
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Down-right diagonal
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn() + 1);
        while(isFreeMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
            position.setValues(position.getRow() + 1, position.getColumn() + 1);
        }
        if(canMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        return possibleMoves;
    }


}
