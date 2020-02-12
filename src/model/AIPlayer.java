package model;

public class AIPlayer {
    private char symbol;

    public AIPlayer(boolean turn) {
        if (turn == TurnConstants.X_TURN) symbol = TurnConstants.x;
        else symbol = TurnConstants.o;
    }

    public char getSymbol() {
        return symbol;
    }
}
