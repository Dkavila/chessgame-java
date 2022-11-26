package me.dkavila.chess.exception;

import me.dkavila.board.entities.Position;
import me.dkavila.board.exception.BoardException;

public class ChessException extends BoardException {
    public ChessException(String msg){
        super(msg);
    }

    public static void invalidChessPosition(){
        throw new ChessException("Error processing ChessPosition. Valid positions are from a1 to h8");
    }

    public static void invalidSourcePosition(Position position){
        throw new ChessException("There is no piece on source position." + "\n Provided position: " + position);
    }
}
