package me.dkavila.application;

import me.dkavila.application.screen.UI;
import me.dkavila.chess.entities.ChessMatch;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.ChessPosition;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while(true){
            UI.printBoard(chessMatch.getChessPieces());
            System.out.print("\nSource: ");
            ChessPosition source = ChessMatch.readChessPosition(scanner.nextLine());
            System.out.print("\nTarget: ");
            ChessPosition target = ChessMatch.readChessPosition(scanner.nextLine());

            ChessPiece capturedPiece = chessMatch.moveChessPiece(source, target);
        }
    }
}
