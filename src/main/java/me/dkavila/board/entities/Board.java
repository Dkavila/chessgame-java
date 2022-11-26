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
            BoardException.invalidRowOrColumn(rows, columns);
        }
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public Piece getPiece(int row, int column){
        if (!positionExists(row, column)) {
            BoardException.positionOutBoard(new Position(row, column));
        }
        return this.pieces[row][column];
    }

    public Piece getPiece(Position position){
        return getPiece(position.getRow(), position.getColumn());
    }

    public void placePiece(Piece piece, Position position){
        if (thereIsAPiece(position)) {
            BoardException.pieceAlreadyExists(position);
        }
        this.pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position){
        if(!positionExists(position)){
            BoardException.positionOutBoard(position);
        }
        if(getPiece(position) == null){
            return null;
        }
        Piece piece = getPiece(position);
        piece.position = null;
        this.pieces[position.getRow()][position.getColumn()] = null;
        return piece;
    }

    private boolean positionExists(int row, int column){
       return row >= 0 && row < this.rows && column >=0 && column < this.columns;
    }

    public Piece makeMove(Position source, Position target){
        Piece piece = removePiece(source);
        Piece capturedPiece = removePiece(target);
        placePiece(piece, target);
        return capturedPiece;
    }

    private boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            BoardException.positionOutBoard(position);
        }
        return getPiece(position) != null;
    }
}
