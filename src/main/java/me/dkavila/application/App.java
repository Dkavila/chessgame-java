package me.dkavila.application;

import me.dkavila.chess.entities.ChessMatch;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getChessPieces());
    }
}
