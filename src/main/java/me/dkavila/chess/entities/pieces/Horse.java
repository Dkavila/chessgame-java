package me.dkavila.chess.entities.pieces;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class Horse extends ChessPiece {

    public Horse(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "H";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position position = new Position(0, 0);

        // Up moves
        position.setValues(getPosition().getRow() - 2, getPosition().getColumn() - 1);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }
        position.setValues(getPosition().getRow() - 2, getPosition().getColumn() + 1);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Right moves
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn()  + 2);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn() + 2);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Left moves
        position.setValues(getPosition().getRow() + 1, getPosition().getColumn() - 2);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }
        position.setValues(getPosition().getRow() - 1, getPosition().getColumn() - 2);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        // Down moves
        position.setValues(getPosition().getRow() + 2, getPosition().getColumn()  - 1);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }
        position.setValues(getPosition().getRow() + 2, getPosition().getColumn() + 1);
        if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position))){
            possibleMoves[position.getRow()][position.getColumn()] = true;
        }

        return possibleMoves;
    }
}
