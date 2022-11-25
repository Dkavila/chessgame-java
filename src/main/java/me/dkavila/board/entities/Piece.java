package me.dkavila.board.entities;

import lombok.Data;

@Data
public class Piece {
    protected Position position;
    private Board board;

    public Piece(Position position) {
        this.position = position;
    }

    protected Board getBoard(){
        return board;
    }

}
