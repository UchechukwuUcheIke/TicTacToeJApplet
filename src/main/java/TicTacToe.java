import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToe extends JFrame {

    private Game currentGame;
    private static final int GRIDSIZE = 3;
    private TicTacToeSquare[][] tictactoeSquares = new TicTacToeSquare[GRIDSIZE][GRIDSIZE];
    private static final int WIDTH = 500;
    private static final int HEIGHT = 520;
    private static final int GAPSIZE = 10;
    private int player;
    private boolean PVP = true;
    private TicTacToeCPU computer;

    private void initGUI() {
        Color LIGHT_BROWN = new Color(164, 136, 98);
        Color LIGHT_GREY = new Color(220, 220, 220);

        // Initiate the Components of the GUI
        // TODO: Create a Jlabel named titleLabel


        Font titleFont = new Font("Grobold", Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        add(titleLabel, BorderLayout.PAGE_START);

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(LIGHT_GREY);
        //TODO: Add gamePanel to JFrame

        gamePanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE, GAPSIZE, GAPSIZE));

        for (int column = 0; column < GRIDSIZE; column++) {
            for (int row = 0; row < GRIDSIZE; row++) {

                // TODO: Create a JButton to act as the buttons in the class

                button.setRow(row);
                button.setColumn(column);

                button.setBorder(null); // remove borders around the box
                button.setFocusPainted(false); // remove borders around box when clicked
                tictactoeSquares[column][row] = button;

                gamePanel.add(button);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = button.getRow();
                        int column = button.getColumn();
                        currentGame.makeMove(row, column);

                        if (PVP) {
                            if (player == 1) {
                                button.setText("X");
                                button.setForeground(Color.RED);
                            } else if (player == 2) {
                                button.setText("O");
                                button.setForeground(Color.BLUE);
                            }

                            button.setFont(buttonFont);

                            if (player == 1) {
                                player = 2;
                            } else if (player == 2) {
                                player = 1;
                            }

                        } else {

                            // Player actions
                            button.setText("X");
                            button.setForeground(Color.RED);
                            button.setFont(buttonFont);

                            // CPU actions
                            if (!currentGame.boardFull()) {
                                Point moveSlot = computer.getMove(currentGame.getBoard());
                                System.out.println("X: " + moveSlot.x + " | Y: " + moveSlot.y);
                                currentGame.makeMove(moveSlot.x, moveSlot.y);
                                TicTacToeSquare cpuButton = tictactoeSquares[moveSlot.y][ moveSlot.x];
                                cpuButton.setText("O");
                                cpuButton.setForeground(Color.BLUE);
                            }
                        }

                        checkWinCondition();
                        if (PVP) {

                        }
                    }
                });
            }
        }

        String message = "Do you wish to play PVP or PvC?";
        String[] optionStrings = {"vs Player", "vs Computer"};
        int option = JOptionPane.showOptionDialog(this, message, "GameMode", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, optionStrings, null);
        if (option == JOptionPane.YES_OPTION) {
            PVP = true;
        } else {
            PVP = false;
        }
    }


    private void checkWinCondition() {
        String message;
        if (currentGame.checkWin()) {
            message = "Player " + player + " Has Won!\nPlay again?";
            int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        } else if (currentGame.boardFull()) {
            message = "Draw Game!\nPlay again?";
            int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void resetGame() {
        currentGame.resetGame();
        player = 1;
        for (int column = 0; column < GRIDSIZE; column++) {
            for (int row = 0; row < GRIDSIZE; row++) {
                tictactoeSquares[column][row].setText("");
            }
        }
    }


    public TicTacToe() {
        setTitle("TicTacToe");
        setResizable(false);
        // TODO: Set size of the GUI to (Width, Height)
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGUI();
        player = 1;

        currentGame = new Game();
        computer = new TicTacToeCPU(CPUDifficulty.Easy);
    }

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {

        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
}