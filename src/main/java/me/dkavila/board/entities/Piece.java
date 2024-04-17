package me.dkavila.board.entities;

import lombok.Data;

@Data
public abstract class Piece {
    protected Position position;
    private final Board board;

    public Piece(Board board) {
        this.board = board;
        this.position = null;
    }

    private int moveCount;

    public abstract boolean[][] possibleMoves();

    // possibleMove() is a concrete implementation of an abstract method.
    // Based on Template Method design pattern
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean [][] possibleMoves = possibleMoves();
        for(int row = 0; row < possibleMoves.length; row++){
            for(int column = 0; column < possibleMoves.length; column++){
                if(possibleMoves[row][column]){
                    return true;
                }
            }
        }
        return false;
    }

    protected Board getBoard(){
        return board;
    }

    public void increaseMoveCount(){
        this.moveCount++;
    }

    public void decreaseMoveCount(){
        this.moveCount--;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
