package me.dkavila.board.entities;

import lombok.Data;
import me.dkavila.board.exception.BoardException;

@Data
public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns){
        if(rows < 1 || columns < 1){
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
        }
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public Piece getPiece(int row, int column){
        if (!positionExists(row, column)) {
            throw new BoardException("Position out of the board.");
        }
        return this.pieces[row][column];
    }

    public Piece getPiece(Position position){
        return getPiece(position.getRow(), position.getColumn());
    }

    public void placePiece(Piece piece, Position position){
        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }
        this.pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row, int column){
       return row >= 0 && row < this.rows && column >=0 && column < this.columns;
    }

    private boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position out of the board." + "\nProvided position: " + position);
        }
        return getPiece(position) != null;
    }
}
