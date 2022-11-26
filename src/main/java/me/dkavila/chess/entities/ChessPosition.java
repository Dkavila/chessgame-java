package me.dkavila.chess.entities;

import lombok.Data;
import me.dkavila.board.entities.Position;
import me.dkavila.chess.exception.ChessException;

@Data
public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row){
        if(column < 'a' || column > 'h' || row < 1 || row > 8){
            ChessException.invalidChessPosition("" + column + row);
        }
        this.column = column;
        this.row = row;
    }

    protected Position toPosition(){
        return new Position((8 - this.row), (this.column - 'a'));
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
    }


    public static ChessPosition readChessPosition(String position){
        try {
            char column = position.charAt(0);
            int row = Integer.parseInt(position.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e){
            ChessException.invalidChessPosition(position);
            return null;
        }
    }

    @Override
    public String toString(){
        return "" + column + row;
    }

}
