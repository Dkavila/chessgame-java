package me.dkavila.application.screen;

import static me.dkavila.application.screen.ConsoleColor.*;

import me.dkavila.chess.entities.ChessMatch;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.Color;

public class UI {

    // Detailed information about clearScreen():
    // http://techno-terminal.blogspot.com/2014/12/clear-command-line-console-and-bold.html
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o terminal: " + e.getMessage());
        }
    }

    public static void printBoard(ChessPiece[][] chessPieces){
        for(int row = 0; row < chessPieces.length; row++){
            System.out.print( ( 8 - row ) + " ");
            for(int column=0; column < chessPieces.length; column++){
                printPiece(chessPieces[row][column], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] chessPieces, boolean[][] possibleMoves){
        for(int row = 0; row < chessPieces.length; row++){
            System.out.print( ( 8 - row ) + " ");
            for(int column=0; column < chessPieces.length; column++){
                printPiece(chessPieces[row][column], possibleMoves[row][column]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    
    public static void printChessBoard(ChessMatch chessMatch){
        printBoard(chessMatch.getChessPieces());
        System.out.println();
        printCapturedPieces(chessMatch);
        System.out.println();
        printTurns(chessMatch);
    }
    
    public static void printChessBoard(ChessMatch chessMatch, boolean[][] possibleMoves){
        printBoard(chessMatch.getChessPieces(), possibleMoves);
        System.out.println();
        printCapturedPieces(chessMatch);
        System.out.println();
        printTurns(chessMatch);
    }

    private static void printPiece(ChessPiece chessPiece, boolean background){
        if(background){
            System.out.print(ANSI_CYAN_BACKGROUND);
        }
        if(chessPiece == null){
            System.out.print("-" + ANSI_RESET);
        } else {
            if(chessPiece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + chessPiece + ANSI_RESET);
            } else {
                System.out.print(ANSI_RED + chessPiece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPieces(ChessMatch chessMatch){
        System.out.println("Captured pieces: ");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.print(chessMatch.getWhiteCaptured().toString());
        System.out.println(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_RED);
        System.out.print(chessMatch.getBlackCaptured().toString());
        System.out.println(ANSI_RESET);
    }

    private static void printTurns(ChessMatch chessMatch){
        System.out.println("Turn: " + chessMatch.getTurn());
        System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
    }
}
