package model;

import java.util.HashMap;

public class Board {
    private char[][] board;
    private Integer turns;

    // Initilialize all board elements to 0
    public Board() {
        board = new char[3][3];
        turns = 0;
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
    }

    public char[][] getBoard() {
        return board;
    }

    private boolean checkRightDiagonal() {
        for (int i = 2; i >= 0; i--) {

        }
    }

    private boolean checkLeftDiagonal() {
        int win = 0;
        for (int i = 0; i < 9; i = i + 4) {
            win = checkXO(i, win);
        }
        return checkValue(win);
    }


    private boolean checkVertical() {
        int win;
        for (int i = 0; i < 3; i++) {
            win = 0;
            for (int v = i; v < i + 7; v = v + 3) {
                win = checkXO(v, win);
            }
            if (win == 3 || win == -3) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal() {
        int win;
        for (int i = 0; i < 3 * 3; i = i + 3) {
            win = 0;
            for (int h = i; h < i + 3; h++) {
                win = checkXO(h, win);
            }
            if (win == 3 || win == -3) {
                return true;
            }
        }
        return false;
    }

    private int checkXO(int h, int win) {
        if (board.get(h).equals("x")) {
            win++;
        } else if (board.get(h).equals("o")) {
            win--;
        }
        return win;
    }

    public Integer checkState(Integer currentPlayer) {
        int boardSize = board.length;
        if (turns == boardSize*boardSize) {
            return 0;
        }

        for (int i = 0; i < board.length; i++) {
            char centerH = board[i][1];
            if (board[i][0] == centerH && centerH == board[i][2]) {
                return currentPlayer;
            }
            char centerV = board[1][i];
            if (board[0][i] == centerV && centerV == board[2][i]) {
                return currentPlayer;
            }
        }

        char centerD = board[1][1];
        if (board[0][0] == centerD && centerD == board[2][2]) {

        }
        if (board[0][2] == centerD && centerD == board[2][0]) {

        }

        return -1;
    }
}
