package me.dkavila.board.exception;

import me.dkavila.board.entities.Position;

public class BoardException extends RuntimeException{
    public BoardException(String msg){
        super(msg);
    }

    public static void invalidRowOrColumn(){
        throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
    }
    public static void positionOutBoard(Position position){
            throw new BoardException("Position out of the board." + "\nProvided position: " + position);
    }

    public static void pieceAlreadyExists(Position position){
        throw new BoardException("There is already a piece in that position." + "\n Provided position: " + position);
    }
}
