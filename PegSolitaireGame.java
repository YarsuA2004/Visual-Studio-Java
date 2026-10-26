import javax.swing.*;
import java.awt.*;

// TODO: Add functionality to select board type, once they are developed!

public class PegSolitaireGame extends JPanel {
    private final Board board;
    private final JButton[][] buttons;
    private final JLabel statusLabel; // Give this functionality to App.java status bar

    private int selectedRow = -1;
    private int selectedCol = -1;

    public PegSolitaireGame(int boardSize, int boardType) {
        this.board = new Board(boardSize, boardType);
        this.buttons = new JButton[boardSize][boardSize];

        setLayout(new BorderLayout(10, 10));

        // Status bar at the top
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        // Grid of clickable cells
        JPanel gridPanel = new JPanel(new GridLayout(boardSize, boardSize, 5, 5));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridPanel.setBackground(new Color(45, 45, 45));
        //gridPanel.setPreferredSize(new Dimension(1200, 800));

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                final int r = row;
                final int c = col;

                JButton button = new JButton();
                button.setFont(new Font("SansSerif", Font.BOLD, 24));
                button.setFocusPainted(false);
                button.setPreferredSize(new Dimension(60, 60));

                button.addActionListener(e -> handleClick(r, c));

                buttons[row][col] = button;
                gridPanel.add(button);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Restart button at the bottom
        //JButton restartButton = new JButton("Restart");
        //restartButton.addActionListener(e -> restartGame());

        //JPanel bottomPanel = new JPanel();
        //bottomPanel.add(restartButton);
        //add(bottomPanel, BorderLayout.SOUTH);

        updateBoardDisplay();
        statusLabel.setText(
            "Pegs remaining: " + board.countPegs()
            + " | Select a peg to move."
        );

    }

    public int getBoardSize() {
        return board.getSize();
    }

    public int getBoardType() {
        return board.getType();
    }

    public Board getBoard() {
        return board;
    }

    // PRE: 
    // POST: handles the response to a user clicking one of the grid buttons
    public void handleClick(int row, int col) {
        // No peg is selected: select a peg
        if (selectedRow == -1) {
            if (board.getCell(row, col) == 1) {
                selectedRow = row;
                selectedCol = col;

                statusLabel.setText(
                    "Peg selected. Click an empty destination."
                );

                updateBoardDisplay();
            } 
            else {
                statusLabel.setText("Select a cell containing a peg.");
            }

            return;
        }

        // Clicking the selected peg again cancels selection
        if (row == selectedRow && col == selectedCol) {
            selectedRow = -1;
            selectedCol = -1;

            statusLabel.setText("Selection cancelled.");
            updateBoardDisplay();
            return;
        }

        // Try to move the selected peg
        boolean moved = board.movePeg(selectedRow, selectedCol, row, col);

        // Clear the selection after the attempted move
        selectedRow = -1;
        selectedCol = -1;

        if (moved) {
            updateBoardDisplay();

            int pegs = board.countPegs();

            if (pegs == 1) {
                statusLabel.setText("Congratulations! You won with one peg!");
            } 
            else if (!board.hasLegalMoves()) {
                statusLabel.setText("Game over! Pegs remaining: " + pegs);
            } 
            else {
                statusLabel.setText("Good move! Pegs remaining: " + pegs);
            }
        } else {
            statusLabel.setText("Invalid move. Select a peg and try again.");
            updateBoardDisplay();
        }
    }

    private void updateBoardDisplay() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                JButton button = buttons[row][col];

                if (board.getCell(row, col) == -1) {
                    button.setText("");
                    button.setBackground(new Color(35, 35, 35));
                }
                else if (board.getCell(row, col) == 1) {
                    button.setText("\u25CF");
                    //button.setForeground(new Color(30, 90, 180));
                    button.setForeground(new Color(80, 80, 80));
                    button.setBackground(new Color(230, 230, 230));
                } else {
                    button.setText("");
                    button.setBackground(new Color(190, 220, 190));
                }

                // Highlight the currently selected peg
                if (row == selectedRow && col == selectedCol) {
                    button.setBackground(new Color(255, 220, 100));
                }
            }
        }
    }

    // public void replayGame() {
    //     int choice = JOptionPane.showConfirmDialog(this, "Replay game with the same board size?", "Replay Game", JOptionPane.YES_NO_OPTION);

    //     if (choice == JOptionPane.YES_OPTION) {
    //         // Reinitialize the board and clear the selection.
    //         // The GUI will be rebuilt with the same dimensions.

    //         new PegSolitaireGame(board.getSize()).setVisible(true);
    //     }
    //     else {
    //         return;
    //     }
    // }
}
