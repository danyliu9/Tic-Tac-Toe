package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import model.Board;

public class TicTacToe extends JFrame{
    private static final char x = 'X';
    private static final char o = 'O';
    private static final Boolean xTurn = true;
    private static final Boolean oTurn = false;

    private boolean turn;

    private Board board;

    public TicTacToe() {
        super("Tic-Tac-Toe");
        board = new Board();
        selectPlayer();
    }

    private void initializeGraphics() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(0,3));
        jPanel1.setPreferredSize(new Dimension(300,300));

        generateButtons(jPanel1);

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);
    }

    private void selectPlayer() {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel jPanel0 = new JPanel();
        jPanel0.setBorder(new TitledBorder("Play as:"));
        jPanel0.setPreferredSize(new Dimension(300,60));
        getContentPane().add(jPanel0);

        setPlayerButton(jPanel0, "Player X", xTurn, "click me!");
        setPlayerButton(jPanel0, "Player O", oTurn, "or click me!");

        pack();
        setLocationRelativeTo(null);
    }

    private void setPlayerButton(JPanel jPanel0, String s, Boolean turn, String s2) {
        JButton jButton = new JButton(s);
        jButton.addActionListener(e -> {
            this.turn = turn;
            remove(jPanel0);
            initializeGraphics();
        });
        jButton.setToolTipText(s2);
        jButton.setPreferredSize(new Dimension(100, 30));
        jPanel0.add(jButton);
    }

    private void generateButtons(JPanel jPanel1) {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                JButton button = new JButton(" ");
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    if (button.getText().equals(" ")) {
                        if (turn) {
                            board.addMarker(finalI, finalJ, x);
                            placeChar(x, button);
                        } else {
                            board.addMarker(finalI, finalJ, o);
                            placeChar(o, button);
                        }
                        turn = !turn;
                    }

                });
                jPanel1.add(button);
            }
        }
    }

    private void placeChar(char playerName, JButton button) {
        button.setEnabled(false);
        ImageIcon imageIcon = new ImageIcon(playerName + ".gif");
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
    }

    private JPanel getEndGamePanel(Label victory) {
        getContentPane().removeAll();
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(300,90));
        getContentPane().add(jPanel);
        jPanel.add(victory);
        JButton jButton2 = new JButton("Play again?");
        jButton2.addActionListener(e -> {
            board.clear();
            getContentPane().removeAll();
            selectPlayer();
        });

        jButton2.setToolTipText("Reset the game");
        jButton2.setPreferredSize(new Dimension(100,20));
        jButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        jPanel.add(jButton2);

        pack();
        return jPanel;
    }

    public static void main(String[] args) {
        new TicTacToe().setVisible(true);
    }
}
