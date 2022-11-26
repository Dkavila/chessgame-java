package me.dkavila.application.screen;

import static me.dkavila.application.screen.ConsoleColor.*;

import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class UI {

    // Detailed information about clearScreen():
    // http://techno-terminal.blogspot.com/2014/12/clear-command-line-console-and-bold.html
    public static void  clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printBoard(ChessPiece[][] chessPieces){
        for(int row = 0; row < chessPieces.length; row++){
            System.out.print( ( 8 - row ) + " ");
            for(int column=0; column < chessPieces.length; column++){
                printPiece(chessPieces[row][column]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece chessPiece){
        if(chessPiece == null){
            System.out.print("-");
        } else {
            if(chessPiece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + chessPiece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + chessPiece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
