import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private final JButton[] buttons;
    private final String[] board;
    private String currentPlayer;
    private final String player1Name;
    private final String player2Name;
    private final JLabel statusLabel;

    public TicTacToeGUI(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        JFrame frame = new JFrame("Tic Tac Toe");
        JPanel boardPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        buttons = new JButton[9];
        board = new String[9];
        currentPlayer = player1Name;

        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); // Increased height for status label
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setForeground(Color.DARK_GRAY);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener(i));
            boardPanel.add(buttons[i]);
        }

        statusLabel = new JLabel(player1Name + "'s turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        boardPanel.setBackground(new Color(173, 200, 235));
        statusPanel.add(statusLabel);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private final int buttonIndex;

        public ButtonClickListener(int index) {
            buttonIndex = index;
        }

        // Inside the ButtonClickListener class
        public void actionPerformed(ActionEvent e) {
            if (buttons[buttonIndex].getText().isEmpty()) {
                buttons[buttonIndex].setText(currentPlayer.equals(player1Name) ? "X" : "O");
                buttons[buttonIndex].setEnabled(false); // Disable button after click
                board[buttonIndex] = currentPlayer;

                int[] winningCombination = checkWinner();
                if (winningCombination != null) {
                    setButtonTextColor(winningCombination, currentPlayer.equals(player1Name) ? Color.RED : Color.BLUE);
                    statusLabel.setText(playerNameFor(currentPlayer) + " wins!");
                    resetBoard();
                } else if (isBoardFull()) {
                    statusLabel.setText("It's a draw!");
                    for (JButton button : buttons) {
                        button.setForeground(Color.MAGENTA); // You can choose a different color here
                    }
                    resetBoard();
                } else {
                    currentPlayer = (currentPlayer.equals(player1Name)) ? player2Name : player1Name;
                    statusLabel.setText(playerNameFor(currentPlayer) + "'s turn");
                }
            }
        }


        private String playerNameFor(String player) {
            return player.equals(player1Name) ? player1Name : player2Name;
        }

        private void setButtonTextColor(int[] combination, Color color) {
            for (int index : combination) {
                buttons[index].setForeground(color);
            }
        }


        private int[] checkWinner() {
            int[][] winningCombinations = {
                    {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
                    {0, 4, 8}, {2, 4, 6}             // Diagonals
            };

            for (int[] combination : winningCombinations) {
                String line = board[combination[0]] + board[combination[1]] + board[combination[2]];
                if (line.equals(playerNameFor(player1Name) + playerNameFor(player1Name) + playerNameFor(player1Name)) ||
                        line.equals(playerNameFor(player2Name) + playerNameFor(player2Name) + playerNameFor(player2Name))) {
                    return combination;
                }
            }

            return null;
        }



        private boolean isBoardFull() {
            for (String cell : board) {
                if (cell.equals("")) {
                    return false;
                }
            }
            return true;
        }

        private void resetBoard() {
            for (int i = 0; i < 9; i++) {
                buttons[i].setIcon(null);
                buttons[i].setBackground(null);
                board[i] = "";
            }
            currentPlayer = "X";
        }
    }
}