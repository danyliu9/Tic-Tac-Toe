package model;

public class AIPlayer {
    private char symbol;

    public AIPlayer(boolean turn) {
        if (turn == TicTacToe.X_TURN) symbol = TicTacToe.x;
        else symbol = TicTacToe.o;
    }

    public char getSymbol() {
        return symbol;
    }
}
