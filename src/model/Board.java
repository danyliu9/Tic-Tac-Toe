package model;

import java.util.HashMap;

public class Board {
    private char[][] board;
    private Boolean w;

    // Initilialize all board elements to 0
    public Board() {
        board = new char[3][3];
    }

    public void addMarker(Integer x, Integer y, char currentPlayer) {
        board[x][y] = currentPlayer;
    }

    public void addO(Integer i) {
        board.replace(i, "o");
    }

    public void clear() {
        for (int i = 0; i < 9; i++) {
            board.replace(i, "");
        }
    }

    public HashMap<Integer, String> getBoard() {
        return board;
    }

    public Integer checkWin() {
        if (checkHorizontal() || checkVertical() || checkRightDiagonal() || checkLeftDiagonal()) {
            return 0;
        } else {
            return 1;
        }
    }

    private boolean checkRightDiagonal() {
        int win = 0;
        for (int i = 2; i < 7; i = i + 2) {
            win = checkXO(i, win);
        }
        return checkValue(win);
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

    private boolean checkValue(int value) {
        return value == 3 || value == -3;
    }
}
