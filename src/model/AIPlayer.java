package model;

import ui.TicTacToe;

public class AIPlayer {
    private char symbol;

    public AIPlayer(boolean turn) {
        if (turn == TicTacToe.xTurn) symbol = TicTacToe.x;
        else symbol = TicTacToe.o;
    }

    public char getSymbol() {
        return symbol;
    }
}
