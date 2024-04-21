package me.dkavila.application;

import me.dkavila.application.screen.UI;
import me.dkavila.board.exception.BoardException;
import me.dkavila.chess.entities.ChessMatch;
import me.dkavila.chess.entities.ChessPiece;
import me.dkavila.chess.entities.ChessPosition;
import me.dkavila.chess.exception.ChessException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while(true){
            try {
                UI.clearScreen();
                UI.printChessBoard(chessMatch);
                System.out.print("\nSource: ");
                ChessPosition source = ChessPosition.readChessPosition(scanner.nextLine());
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                
                UI.clearScreen();
                UI.printChessBoard(chessMatch, possibleMoves);
                System.out.print("\nTarget: ");
                ChessPosition target = ChessPosition.readChessPosition(scanner.nextLine());
                ChessPiece capturedPiece = chessMatch.moveChessPiece(source, target);
            }catch (ChessException chessException) {
                System.out.println(chessException.getMessage());
                scanner.nextLine();
            }catch (BoardException boardException) {
                System.out.println(boardException.getMessage());
                scanner.nextLine();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                scanner.close();
                break;
            }
        }
    }
}
