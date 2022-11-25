package me.dkavila.application;

import me.dkavila.chess.entities.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] chessPieces){
        for(int row = 0; row < chessPieces.length; row++){
            System.out.print( ( 8 - row ) + " ");
            for(int column=0; column < chessPieces.length; column++){
                printPiece(chessPieces[row][column]);
            }
            System.out.println();
        }
        System.out.print("  A B C D E F G H");
    }

    private static void printPiece(ChessPiece chessPiece){
        if(chessPiece == null){
            System.out.print("-");
        } else {
            System.out.print(chessPiece);
        }
        System.out.print(" ");
    }
}
