import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private final JFrame frame;
    private final JTextField player1TextField;
    private final JTextField player2TextField;

    public MainMenu() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250); // Increased height to accommodate the button
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel player1Label = new JLabel("Player X Name:");
        JLabel player2Label = new JLabel("Player O Name:");
        player1TextField = new JTextField(20); // Increase the column count
        player2TextField = new JTextField(20); // Increase the column count

        inputPanel.setBackground(new Color(180, 200, 255));
        player1Label.setFont(new Font("Arial", Font.BOLD, 14));
        player2Label.setFont(new Font("Arial", Font.BOLD, 14));
        player1TextField.setFont(new Font("Arial", Font.PLAIN, 14));
        player2TextField.setFont(new Font("Arial", Font.PLAIN, 14));

        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPanel.add(player1Label, constraints);

        constraints.gridx = 1;
        inputPanel.add(player1TextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(player2Label, constraints);

        constraints.gridx = 1;
        inputPanel.add(player2TextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Span across two columns
        constraints.anchor = GridBagConstraints.CENTER;
        JButton startButton = new JButton("Start Tic Tac Toe");
        inputPanel.add(startButton, constraints);

        startButton.addActionListener(e -> {
            String player1Name = player1TextField.getText();
            String player2Name = player2TextField.getText();

            frame.dispose(); // Close the main menu
            new TicTacToeGUI(player1Name, player2Name); // Start the Tic Tac Toe game with player names
        });

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
