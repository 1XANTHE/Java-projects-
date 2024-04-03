
//E23CSEU0347
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class for the Tic Tac Toe game
public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[][] grid; // Grid of buttons to represent the game board
    private final JLabel turnLabel; // Label to display whose turn it is
    private boolean isXTurn = true; // Indicates whose turn it is (true for X, false for O)
    private int moves = 0; // Counter for the number of moves made in the game

    // Constructor method to initialize the game window
    public TicTacToe() {
        setTitle("Tic Tac Toe"); // Set window title
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set default close operation
        setSize(300, 350); // Set window size
        setLocationRelativeTo(null); // Center the window on the screen

        turnLabel = new JLabel("X's Turn"); // Initialize the turn label with default text
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size for the turn label
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label
        add(turnLabel, BorderLayout.NORTH); // Add turn label to the top of the window

        JPanel gamePanel = new JPanel(new GridLayout(3, 3)); // Panel to hold the game grid
        gamePanel.setBackground(Color.DARK_GRAY); // Set background color of the game panel

        grid = new JButton[3][3]; // Initialize the grid of buttons
        for (int row = 0; row < 3; row++) { // Loop through rows
            for (int col = 0; col < 3; col++) { // Loop through columns
                grid[row][col] = new JButton(); // Create a new button for each cell
                grid[row][col].setFont(new Font("Arial", Font.PLAIN, 40)); // Set font and size for the button text
                grid[row][col].addActionListener(this); // Add action listener to handle button clicks
                grid[row][col].setBackground(Color.DARK_GRAY); // Set background color of the button
                gamePanel.add(grid[row][col]); // Add button to the game panel
            }
        }

        add(gamePanel, BorderLayout.CENTER); // Add game panel to the center of the window

        setVisible(true); // Make the window visible
    }
//E23CSEU0347
    // ActionListener interface method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource(); // Get the button that was clicked

        // Check if the clicked button is empty and it's currently a player's turn
        if (clickedButton.getText().equals("") && !turnLabel.getText().equals("")) {
            // Set the text of the clicked button to X or O based on whose turn it is
            if (isXTurn) {
                clickedButton.setText("X"); // Set text to X for player X's turn
                clickedButton.setForeground(new Color(0, 128, 0)); // Set color to slight dark green for X
                turnLabel.setText("O's Turn"); // Update turn label to indicate player O's turn
            } else {
                clickedButton.setText("O"); // Set text to O for player O's turn
                clickedButton.setForeground(Color.WHITE); // Set color to white for O
                turnLabel.setText("X's Turn"); // Update turn label to indicate player X's turn
            }
            //E23CSEU0347
            isXTurn = !isXTurn; // Switch turns
            moves++; // Increment move counter
            // Check for winner or tie
            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + (isXTurn ? "O" : "X") + " wins!"); // Show winner message
                resetGame(); // Reset the game
            } else if (moves == 9) {
                JOptionPane.showMessageDialog(this, "It's a tie!"); // Show tie message
                resetGame(); // Reset the game
            }
        }
    }

    // Method to check if there is a winner
    private boolean checkWinner() {
        // Check rows for a winner
        for (int row = 0; row < 3; row++) {
            if (grid[row][0].getText().equals(grid[row][1].getText()) &&
                    grid[row][0].getText().equals(grid[row][2].getText()) &&
                    !grid[row][0].getText().equals("")) {
                return true; // Return true if all buttons in the row have the same non-empty text
            }
        }
        // Check columns for a winner
        for (int col = 0; col < 3; col++) {
            if (grid[0][col].getText().equals(grid[1][col].getText()) &&
                    grid[0][col].getText().equals(grid[2][col].getText()) &&
                    !grid[0][col].getText().equals("")) {
                return true; // Return true if all buttons in the column have the same non-empty text
            }
        }
        // Check diagonals for a winner
        if (grid[0][0].getText().equals(grid[1][1].getText()) &&
                grid[0][0].getText().equals(grid[2][2].getText()) &&
                !grid[0][0].getText().equals("")) {
            return true; // Return true if all buttons in the diagonal have the same non-empty text
        }
        return grid[0][2].getText().equals(grid[1][1].getText()) &&
                grid[0][2].getText().equals(grid[2][0].getText()) &&
                !grid[0][2].getText().equals(""); // Return true if all buttons in the diagonal have the same non-empty text
// Return false if there is no winner
    }

    // Method to reset the game
    //E23CSEU0347
    private void resetGame() {
        // Clear the text of all buttons in the grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col].setText("");
            }
        }
        isXTurn = true; // Reset the turn to player X
        turnLabel.setText("X's Turn"); // Update turn label to indicate player X's turn
        moves = 0; // Reset move counter
    }

    // Main method to create an instance of the TicTacToe class and start the game
    public static void main(String[] args) {
        new TicTacToe();
    }
}
