package me.dkavila.chess.exception;

import me.dkavila.board.exception.BoardException;
import me.dkavila.chess.entities.ChessPosition;
import me.dkavila.chess.entities.Color;

public class ChessException extends BoardException {
    public ChessException(String msg){
        super(msg);
    }

    public static void invalidChessPosition(String position){
        throw new ChessException("Error processing ChessPosition. Valid positions are from a1 to h8." +
                "\nProvided position: " + position);
    }

    public static void invalidSourcePosition(ChessPosition chessPosition){
        throw new ChessException("There is no piece on source position." +
                "\nProvided position: " + chessPosition);
    }

    public static void opponentPieceSelected(Color currentPlayer){
        throw new ChessException("The chosen piece is not yours, you can't move it." +
                "\nYou can only move " + currentPlayer.toString().toLowerCase() + " pieces.");
    }

    public static void noPossibleMoves(){
        throw new ChessException("There is no possible moves for the chosen piece.");
    }

    public static void invalidMove(){
        throw new ChessException("The chosen piece can't move to target position.");
    }
}
