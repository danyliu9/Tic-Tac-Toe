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
    private static final String x = "X";
    private static final String o = "O";
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
        for (int i = 0; i < board.getBoard().size(); i++) {
            JButton button = new JButton(board.getBoard().get(i));
            int finalI = i;
            button.addActionListener(e -> {
                if (button.getText().equals("")) {
                    if (turn) {
                        board.addX(finalI);
                        placeChar(x, button);
                    } else {
                        board.addO(finalI);
                        placeChar(o, button);
                    }
                    turn = !turn;
                }

            });
            jPanel1.add(button);
        }
    }

    private void placeChar(String playerName, JButton button) {
        button.setEnabled(false);
        ImageIcon imageIcon = new ImageIcon(playerName + ".gif");
        button.setIcon(imageIcon);
        button.setDisabledIcon(imageIcon);
        System.out.println(board.getBoard().size());
        if (board.checkWin()) {
            winScreen(playerName);
        }
    }

    private void winScreen(String player) {
        getContentPane().removeAll();
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(300,70));
        getContentPane().add(jPanel);
        Label victory = new Label("PLAYER " + player + " WINS!");
        victory.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        victory.setAlignment(Label.CENTER);
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
        jPanel.add(jButton2);

        pack();

        Random rand = new Random();
        jPanel.setBackground(new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)));
    }

    public static void main(String[] args) {
        new TicTacToe().setVisible(true);
    }
}
