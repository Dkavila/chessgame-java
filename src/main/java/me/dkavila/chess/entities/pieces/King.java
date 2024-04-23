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

    private boolean testRookCastle(Position position){
        ChessPiece piece = (ChessPiece)getBoard().getPiece(position);
        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
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

        // Special Move - Castling
        if(getMoveCount() == 0){
            Position kingRookPosition = new Position(getPosition().getRow(), getPosition().getColumn() + 3);
            if(testRookCastle(kingRookPosition)){
                Position position1 = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                Position position2 = new Position(getPosition().getRow(), getPosition().getColumn() + 2);
                if(getBoard().getPiece(position1) == null && getBoard().getPiece(position2) == null){
                    possibleMoves[getPosition().getRow()][getPosition().getColumn() + 2] = true;
                }
            }

            Position queenRookPosition = new Position(getPosition().getRow(), getPosition().getColumn() - 4);
            if(testRookCastle(queenRookPosition)){
                Position position1 = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                Position position2 = new Position(getPosition().getRow(), getPosition().getColumn() - 2);
                Position position3 = new Position(getPosition().getRow(), getPosition().getColumn() - 3);
                if(getBoard().getPiece(position1) == null && getBoard().getPiece(position2) == null && getBoard().getPiece(position3) == null){
                    possibleMoves[getPosition().getRow()][getPosition().getColumn() - 2] = true;
                }
            }

        }

        return possibleMoves;
    }

}
