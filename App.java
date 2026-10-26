import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.*;

// BEFORE TURNING IN SPRINT 2:
// TODO: Make Test Cases!
// TODO: Make Sprint 2 Document (from Canvas)

// AFTER SPRINT 2:
//
// TODO: MOST IMPORTANT! SEPARATE App CLASS INTO View AND Controller!
//
// TODO: Errors output to screen
// TODO: Selection or randomization of starting cell
// TODO: Implement formatted JTextField for boardSize (only integers, <= 23)
// TODO: Implement winning/losing screen
// TODO: Replay board
// TODO: Movement Stack (for undo)
// TODO: Hint system
// TODO: Automatic Solver
// TODO: Game info / stats in south subpanel
// TODO: Commenting all code! (PRE: / POST:)

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Peg Solitaire");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setLocationRelativeTo(null);

        // Instantiating first border panels
        JPanel northPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        // Adding first border panels
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Making border settings
        Border line = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border margin = BorderFactory.createEmptyBorder(4, 4, 4, 4); // Outside space
        Border padding = BorderFactory.createEmptyBorder(8, 8, 8, 8); // Inside space
        // Nesting line and padding
        Border lineAndPadding = BorderFactory.createCompoundBorder(line, padding);
        // Nesting the margin outside of the line and padding
        Border finalBorder = BorderFactory.createCompoundBorder(margin, lineAndPadding);

        // Setting the borders
        northPanel.setBorder(finalBorder);
        eastPanel.setBorder(finalBorder);
        southPanel.setBorder(finalBorder);
        westPanel.setBorder(finalBorder);

        //
        // NORTH subpanels (Contain Buttons and Game Label)
        //

        northPanel.setLayout(new GridLayout(1, 2));

        JPanel northSubLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JLabel gameLabel = new JLabel("Peg Solitaire");

        northSubLblPanel.add(gameLabel);
        
        JPanel northSubBtnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton replayBtn = new JButton("Replay Board"); // Replay the current board
        JButton newGameBtn = new JButton("New Game"); // Create a new board with the selected settings
        JButton undoBtn = new JButton("Undo"); // Set the board to before the last move (IMPLEMENT MOVE STACK)
        JButton hintBtn = new JButton("Hint"); // Show a possible move

        northSubBtnPanel.add(replayBtn);
        northSubBtnPanel.add(newGameBtn);
        northSubBtnPanel.add(undoBtn);
        northSubBtnPanel.add(hintBtn);

        northPanel.add(northSubLblPanel);
        northPanel.add(northSubBtnPanel);

        northSubLblPanel.setMaximumSize(northSubLblPanel.getPreferredSize());
        northSubBtnPanel.setMaximumSize(northSubBtnPanel.getPreferredSize());

        northSubLblPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        northSubBtnPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //
        // EAST subpanels
        //

        //
        // SOUTH subpanels
        //

        // PUT GAME INFO / STATS IN SOUTH SUBPANELS (i.e. Board Size, # tiles obtained, # tiles left, # moves made, game runtime, etc.)

        //
        // WEST subpanels
        //

        // PUT USER INPUT SIZE / BOARD CHOICE IN WEST SUBPANELS

        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        JPanel radioButtonPanel = new JPanel(); // radioButtonPanel will have 4 rows, one JLabel and a 3 JRadioButton group
        radioButtonPanel.setMaximumSize(new Dimension(200, 200));
        radioButtonPanel.setLayout(new GridLayout(4, 1));
        radioButtonPanel.setBorder(finalBorder);

        JLabel radioButtonPanelGameTypeLabel = new JLabel("Select type of board:");
        radioButtonPanel.add(radioButtonPanelGameTypeLabel);

        JRadioButton englishRadioButton = new JRadioButton("English");
        JRadioButton hexagonRadioButton = new JRadioButton("Hexagon");
        JRadioButton diamondRadioButton = new JRadioButton("Diamond");

        ButtonGroup radioButtonPanelGroup = new ButtonGroup(); // radioButtonPanelGroup holds the radio buttons
        radioButtonPanelGroup.add(englishRadioButton);
        radioButtonPanelGroup.add(hexagonRadioButton);
        radioButtonPanelGroup.add(diamondRadioButton);

        englishRadioButton.setSelected(true);

        radioButtonPanel.add(englishRadioButton);
        radioButtonPanel.add(hexagonRadioButton);
        radioButtonPanel.add(diamondRadioButton);

        JPanel boardSizePanel = new JPanel(); // boardSizePanel will have 2 rows and 1 column, one JLabel and a JTextArea (two digit places)
        boardSizePanel.setMaximumSize(new Dimension(200, 80));
        boardSizePanel.setLayout(new GridLayout(2, 1));
        boardSizePanel.setBorder(finalBorder);

        JLabel boardSizePanelLabel = new JLabel("Select Board Size:");
        boardSizePanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel boardSizeSubPanel = new JPanel();

        JTextField boardSizeSubPanelTextField = new JTextField(2);
        //boardSizePanelTextField.setMaximumSize(new Dimension(50, 50));

        boardSizeSubPanel.add(boardSizeSubPanelTextField);

        boardSizePanel.add(boardSizePanelLabel);
        boardSizePanel.add(boardSizeSubPanel);

        westPanel.add(radioButtonPanel);
        westPanel.add(boardSizePanel);

        //
        // CENTER subpanels
        //

        // PUT GAME IN CENTER SUBPANELS

        centerPanel.setLayout(new BorderLayout());

        JPanel centerGamePanelWrapper = new JPanel();
        centerGamePanelWrapper.setLayout(new BorderLayout());

        PegSolitaireGame game = new PegSolitaireGame(7, 1);

        centerGamePanelWrapper.add(game, BorderLayout.CENTER);

        centerPanel.add(centerGamePanelWrapper, BorderLayout.CENTER);
        centerPanel.validate();
        centerPanel.repaint();
    
        //
        // CONTROLLER SECTION
        //

        englishRadioButton.setActionCommand("1");
        hexagonRadioButton.setActionCommand("2");
        diamondRadioButton.setActionCommand("3");

        //replayBtn.addActionListener(e -> replayAction(game, centerGamePanelWrapper, centerPanel));
        newGameBtn.addActionListener(e -> newGame(centerGamePanelWrapper, centerPanel, radioButtonPanelGroup, boardSizeSubPanelTextField));
        //undoBtn.addActionListener(e -> );
        //hintBtn.addActionListener(e -> );
        
        // PACK FRAME, SET VISIBLE
        frame.pack();
        frame.setVisible(true);
    }

    public static void newGame(JPanel centerGamePanelWrapper, JPanel centerPanel, ButtonGroup buttonGroup, JTextField boardSizeTextField) {
        ButtonModel buttonSelection = buttonGroup.getSelection();
        String currentSelection = buttonSelection.getActionCommand();
        int boardTypeSelection = Integer.parseInt(currentSelection);

        String textInput = boardSizeTextField.getText();
        int boardSizeSelection = Integer.parseInt(textInput);

        centerGamePanelWrapper.removeAll();
        centerGamePanelWrapper.validate();
        centerGamePanelWrapper.repaint();
        PegSolitaireGame newGame = new PegSolitaireGame(boardSizeSelection, boardTypeSelection);
        centerGamePanelWrapper.add(newGame, BorderLayout.CENTER);
        centerGamePanelWrapper.validate();
        centerGamePanelWrapper.repaint();

        revalidateCenterPanel(centerPanel);
        return;
    }

    public static void replayAction(PegSolitaireGame game, JPanel centerGamePanelWrapper, JPanel centerPanel) {
        centerGamePanelWrapper.removeAll();
        centerGamePanelWrapper.validate();
        centerGamePanelWrapper.repaint();
        PegSolitaireGame replayedGame = new PegSolitaireGame(game.getBoardSize(), game.getBoardType());
        centerGamePanelWrapper.add(replayedGame, BorderLayout.CENTER);
        centerGamePanelWrapper.validate();
        centerGamePanelWrapper.repaint();

        revalidateCenterPanel(centerPanel);
    }

    public static void revalidateCenterPanel(JPanel centerPanel) {
        centerPanel.validate();
        centerPanel.repaint();
    }

}
