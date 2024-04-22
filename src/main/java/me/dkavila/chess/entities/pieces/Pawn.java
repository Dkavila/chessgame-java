package me.dkavila.chess.entities.pieces;

import me.dkavila.board.entities.Board;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.entities.ChessMatch;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

        // Special Move - En Passant
        ChessPiece enPassantVulnerable = chessMatch.getEnPassantVulnerable();
        if(enPassantVulnerable != null){
            Position left = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
            Position futurePosition = new Position(left.getRow() - direction, getPosition().getColumn() - 1);
            if(canCapture(left) && enPassantVulnerable.equals(getBoard().getPiece(left)) && isFreeMove(futurePosition)){
                possibleMoves[left.getRow() - direction][left.getColumn()] = true;
            }
            Position right = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
            futurePosition = new Position(right.getRow() - direction, getPosition().getColumn() + 1);
            if(canCapture(right) && enPassantVulnerable.equals(getBoard().getPiece(right)) && isFreeMove(futurePosition)){
                possibleMoves[right.getRow() - direction][right.getColumn()] = true;
            }
        }
        
        return possibleMoves;
    }
}
