package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class TicTacToe extends JFrame{
    public static final char x = 'X';
    public static final char o = 'O';
    public static final Boolean xTurn = true;
    public static final Boolean oTurn = false;
    private AIPlayer ai;

    private class TButton {
        public JButton jButton;
        private int x;
        private int y;

        public TButton(int x, int y, JButton jButton) {
            this.x = x;
            this.y = y;
            this.jButton = jButton;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private List<TButton> buttons;

    private boolean turn;
    private int gameMode;
    private Board board;

    public TicTacToe() {
        super("Tic-Tac-Toe");
        board = new Board();
        buttons = new ArrayList<>();
        initFrame();
        selectGameMode();
        pack();
        setLocationRelativeTo(null);
    }

    private void initFrame() {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        pack();
    }

    private void selectGameMode() {
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new TitledBorder("Select game mode:"));
        jPanel.setPreferredSize(new Dimension(300,90));
        getContentPane().add(jPanel);

        // Button to initilialize single player mode vs AI
        JButton singlePlayerMode = new JButton("Single Player");
        singlePlayerMode.addActionListener(e -> {
            // TODO
            remove(jPanel);
            gameMode = 1;
            startSinglePlay();
        });
        singlePlayerMode.setToolTipText("Play against the game.");
        singlePlayerMode.setPreferredSize(new Dimension(150, 30));
        jPanel.add(singlePlayerMode);

        // Button to initilialize two player mode
        JButton twoPlayerMode = new JButton("Two Players");
        twoPlayerMode.addActionListener(e -> {
            remove(jPanel);
            gameMode = 2;
            selectPlayerWindow();
        });
        twoPlayerMode.setToolTipText("Play with a friend.");
        twoPlayerMode.setPreferredSize(new Dimension(150, 30));
        jPanel.add(twoPlayerMode);
        pack();
    }

    private void startSinglePlay() {
        selectPlayerWindow();
    }

    private void initAI(boolean player) {
        ai = new AIPlayer(player);
    }

    private void selectPlayerWindow() {
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new TitledBorder("Play as:"));
        jPanel.setPreferredSize(new Dimension(300,60));
        getContentPane().add(jPanel);

        setPlayerButton(jPanel, "Player X", xTurn, "click me!");
        setPlayerButton(jPanel, "Player O", oTurn, "or click me!");
        pack();
    }

    private void initBoardUI() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(0,3));
        jPanel1.setPreferredSize(new Dimension(300,300));

        generateButtons(jPanel1);

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);
    }

    private void setPlayerButton(JPanel jPanel, String s, Boolean turn, String s2) {
        JButton jButton = new JButton(s);
        jButton.addActionListener(e -> {
            this.turn = turn;
            remove(jPanel);
            initBoardUI();
            if (gameMode == 1) {
                boolean player = turn;
                initAI(!player);
            }
        });
        jButton.setToolTipText(s2);
        jButton.setPreferredSize(new Dimension(100, 30));
        jPanel.add(jButton);
    }

    private void generateButtons(JPanel jPanel1) {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                JButton button = new JButton(" ");
                buttons.add(new TButton(i,j, button));
                button.setFocusable(false);
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    if (button.getText().equals(" ")) {
                        if (turn) {
                            board.addMarker(finalI, finalJ, x);
                            placeChar(x, button);
                            if (gameMode == 1){

                                Random rand = new Random();
                                int pos = rand.nextInt(9);


                                int x = pos % board.getBoard().length;
                                int y = pos / board.getBoard().length;
                                while (board.getBoard()[x][y] != ' ') {
                                    pos = rand.nextInt(9);
                                    x = pos % board.getBoard().length;
                                    y = pos / board.getBoard().length;
                                }
                                JButton click = buttons.get(pos).jButton;
                                board.addMarker(x,y,ai.getSymbol());
                                placeChar(ai.getSymbol(), click);
                            }
                        } else {
                            board.addMarker(finalI, finalJ, o);
                            placeChar(o, button);
                            if (gameMode == 1){

                                Random rand = new Random();
                                int pos = rand.nextInt(9);


                                int x = pos % board.getBoard().length;
                                int y = pos / board.getBoard().length;
                                while (board.getBoard()[x][y] != ' ') {
                                    pos = rand.nextInt(9);
                                    x = pos % board.getBoard().length;
                                    y = pos / board.getBoard().length;
                                }
                                JButton click = buttons.get(pos).jButton;
                                board.addMarker(x,y,ai.getSymbol());
                                placeChar(ai.getSymbol(), click);
                            }
                        }
                        if (gameMode == 2) turn = !turn;
                    }

                });
                jPanel1.add(button);
            }
        }
    }

    private void placeChar(char playerName, JButton button) {
        button.setEnabled(false);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("src/ui/"+playerName + ".gif"));
        button.setIcon(imageIcon);
        button.setDisabledIcon(imageIcon);

        switch(board.checkState(playerName)) {
            case '0':
                drawScreen();
                break;
            case x:
                winScreen(x);
                break;
            case o:
                winScreen(o);
                break;
            case ' ':
                break;
        }
    }

    private void winScreen(char player) {
        Label victory = new Label("PLAYER " + player + " WINS!");
        victory.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        victory.setAlignment(Label.CENTER);

        JPanel jPanel = getEndGamePanel(victory);

        Random rand = new Random();
        Color randomColor = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200));
        victory.setBackground(randomColor);
        jPanel.setBackground(randomColor);
    }

    private void drawScreen() {
        Label victory = new Label("IT'S A DRAW!");
        victory.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        victory.setAlignment(Label.CENTER);
        getEndGamePanel(victory);
    }

    private JPanel getEndGamePanel(Label victory) {
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(300,70));
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        getContentPane().add(jPanel);
        jPanel.add(victory);
        JButton jButton2 = new JButton("Play again?");
        jButton2.addActionListener(e -> {
            board.clear();
            getContentPane().removeAll();
            selectGameMode();
        });

        jButton2.setToolTipText("Reset the game");
        jButton2.setPreferredSize(new Dimension(100,20));
        jButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(jButton2);

        pack();
        return jPanel;
    }

    public static void main(String[] args) {
        new TicTacToe().setVisible(true);
    }
}
