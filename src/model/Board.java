package model;


// TODO: extract a board interface so you can have a BIGGER board, oooooo!
/* Class that represents your classic 3x3 board. */
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

    public void addMarker(Integer row, Integer col, char currentPlayer) {
        board[row][col] = currentPlayer;
        this.turns++;
    }

    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
        this.turns = 0;
        this.status = 0;
    }

    public char[][] getBoard() {
        return board;
    }

    public char checkState(char currentPlayer) {
        // TODO: Find a more efficient method of checking state given larger boards.
        // e.g. If we have an n*n board, each row/column only needs to have n of the same player. How do you deal with diagonals?
        // countPerRow = [0: counts in row 0, 1:[countX, countY], 2]
        // countPerCol = [0,1,2]
        // countPerDiag = [0, 1:(countX, countY)]

        // Check win through horizontal/vertical lines
        for (int i = 0; i < board.length; i++) {
            char centerH = board[i][1];

            // Check win horizontally
            if (centerH == currentPlayer) {
                if (board[i][0] == centerH && centerH == board[i][2]) {
                    this.status = 1;
                    return currentPlayer;
                }
            }

            // Check win vertically
            char centerV = board[1][i];
            if (centerV == currentPlayer) {
                if (board[0][i] == centerV && centerV == board[2][i]) {
                    this.status = 1;
                    return currentPlayer;
                }
            }
        }
        
        // Check win through diagonal lines
        char centerD = board[1][1];
        if (centerD == currentPlayer) {
            if (board[0][0] == centerD && centerD == board[2][2]) {
                this.status = 1;
                return currentPlayer;
            }
            if (board[0][2] == centerD && centerD == board[2][0]) {
                this.status = 1;
                return currentPlayer;
            }
        }
        return checkDraw();
    }

    private char checkDraw() {
        int boardSize = board.length;
        if (turns == boardSize*boardSize) {
            this.status = 1;
            return '0';
        }
        return ' ';
    }
}
