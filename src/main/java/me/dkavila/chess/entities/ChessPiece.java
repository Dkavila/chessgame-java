package me.dkavila.chess.entities;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Piece;
import me.dkavila.board.entities.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean canMove(Position target) {
        if (getBoard().positionExists(target)) {
            ChessPiece piece = (ChessPiece) getBoard().getPiece(target);
            return piece == null ||
                    isThereOpponentPiece(target);
        }
        return false;
    }

    protected boolean isFreeMove(Position target){
        return getBoard().positionExists(target) && !getBoard().thereIsAPiece(target);
    }

    protected boolean canCapture(Position target){
        if(getBoard().positionExists(target) && isThereOpponentPiece(target)){
            return true;
        }
        return false;
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece piece = (ChessPiece) getBoard().getPiece(position);
        return piece != null && piece.getColor() != this.color;
    }
}
