package me.dkavila.board.entities;

import lombok.Data;

@Data
public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece getPiece(int row, int column){
        return pieces[row][column];
    }

    public Piece getPiece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }

}
