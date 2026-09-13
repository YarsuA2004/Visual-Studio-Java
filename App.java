import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.*;

public class App {
    public static void main(String[] args) {
            JFrame frame = new JFrame("Example GUI");
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
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

            //
            // NORTH subpanels
            //

            JLabel exampleLabel = new JLabel("This is an example GUI for my project.");
            northPanel.add(exampleLabel);

            JButton button = new JButton("Click Me!");
            northPanel.add(button);

            //
            // EAST subpanels
            //

            JLabel eastJLabel = new JLabel("This JLabel is part of the eastPanel!");
            eastPanel.add(eastJLabel);

            //
            // SOUTH subpanels
            //

            //
            // WEST subpanels
            //

            JPanel radioButtonPanel = new JPanel();
            // radioButtonPanel will have 4 rows, one JLabel and a 3 JRadioButton group
            radioButtonPanel.setLayout(new GridLayout(4, 1));
            westPanel.add(radioButtonPanel, BorderLayout.NORTH);

            JLabel radioButtonPanelGameTypeLabel = new JLabel("Select type of board:");
            radioButtonPanel.add(radioButtonPanelGameTypeLabel);

            JRadioButton englishRadioButton = new JRadioButton("English");
            JRadioButton hexagonRadioButton = new JRadioButton("Hexagon");
            JRadioButton diamondRadioButton = new JRadioButton("Diamond");

            ButtonGroup radioButtonPanelGroup = new ButtonGroup();
            radioButtonPanelGroup.add(englishRadioButton);
            radioButtonPanelGroup.add(hexagonRadioButton);
            radioButtonPanelGroup.add(diamondRadioButton);

            radioButtonPanel.add(englishRadioButton);
            radioButtonPanel.add(hexagonRadioButton);
            radioButtonPanel.add(diamondRadioButton);

            //
            // CENTER subpanels
            //

            JLabel centerCheckBoxLabel = new JLabel("This is an example of a checkbox:");
            JCheckBox centerCheckBox = new JCheckBox();
            centerPanel.add(centerCheckBoxLabel);
            centerPanel.add(centerCheckBox);

            frame.pack();
            frame.setVisible(true);
    }
}

