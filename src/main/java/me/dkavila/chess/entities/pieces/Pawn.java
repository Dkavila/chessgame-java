package me.dkavila.chess.entities.pieces;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position position = new Position(0,0);

        // 1 means that the Pawn will go up in the board (White piece)
        // -1 means that the Pawn will go down in the board (Black piece)
        int direction = getColor() == Color.WHITE ? 1 : -1;

        // Pawn move
        position.setValues(getPosition().getRow() - direction , getPosition().getColumn());
        if(isFreeMove(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
            position.setRow(position.getRow() - direction);
            if(isFreeMove(position) && getMoveCount() == 0){
                possibleMoves[position.getRow()][position.getColumn()] = true;
            }
        }

        // Pawn capture left
        position.setValues(getPosition().getRow() - direction, getPosition().getColumn() - direction);
        if(canCapture(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }
        // Pawn capture right
        position.setValues(getPosition().getRow() - direction, getPosition().getColumn() + direction);
        if(canCapture(position)){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        return possibleMoves;
    }
}
