package model;

public class Board {
    private char[][] board;
    private Integer turns;
    public Integer status;

    // Initilialize all board elements to 0
    public Board() {
        board = new char[3][3];
        clear();
        turns = 0;
        status = 0;
    }

    public void addMarker(Integer x, Integer y, char currentPlayer) {
        board[x][y] = currentPlayer;
        turns++;
    }

    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
        turns = 0;
        status = 0;
    }

    public char[][] getBoard() {
        return board;
    }

    public char checkState(char currentPlayer) {
        for (int i = 0; i < board.length; i++) {
            char centerH = board[i][1];
            if (centerH == currentPlayer) {
                if (board[i][0] == centerH && centerH == board[i][2]) {
                    status = 1;
                    return currentPlayer;
                }
            }
            char centerV = board[1][i];
            if (centerV == currentPlayer) {
                if (board[0][i] == centerV && centerV == board[2][i]) {
                    status = 1;
                    return currentPlayer;
                }
            }
        }

        char centerD = board[1][1];
        if (centerD == currentPlayer) {
            if (board[0][0] == centerD && centerD == board[2][2]) {
                status = 1;
                return currentPlayer;
            }
            if (board[0][2] == centerD && centerD == board[2][0]) {
                status = 1;
                return currentPlayer;
            }
        }
        return checkDraw();
    }

    private char checkDraw() {
        int boardSize = board.length;
        if (turns == boardSize*boardSize) {
            status = 1;
            return '0';
        }
        return ' ';
    }
}
