package ui;

import model.Simulation;
import model.Object;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.Random;

public class AddRandomObjectsWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Simulation simulation;
    private JTextField textField;
    private static int MAX_MASS = 100;
    private static int MAX_X_POSITION = 700;
    private static int MAX_Y_POSITION = 700;
    private static int MAX_X_VELOCITY = 20;
    private static int MAX_Y_VELOCITY = 20;

    private static int WHITESPACE = 7;

    public AddRandomObjectsWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    public void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Add Random Objects");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 100);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        initializeMainPanel();
        this.frame.setVisible(true);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 0));
        mainPanel.setBackground(Color.WHITE);
        initializeFieldPanels();
        initializeChangeNameButton();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));

        frame.add(mainPanel);
    }

    private void initializeFieldPanels() {
        mainPanel.add(createPanel());
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Number of Objects: ");
        panel.add(label);

        textField = new JTextField("0");
        panel.add(textField);
        
        return panel;
    }

    private void initializeChangeNameButton() {
        JButton changeNameButton = createChangeNameButton();
        mainPanel.add(changeNameButton);
    }

    private JButton createChangeNameButton() {
        JButton button = new JButton("Add Objects");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                for (int i = 0; i < Double.parseDouble(textField.getText()); i++) {
                    simulation.addObject(new Object(random.nextDouble() * MAX_MASS * SimulationPanel.SCALE,
                                                   (random.nextDouble() - 0.5) * MAX_X_POSITION * SimulationPanel.SCALE,
                                                   (random.nextDouble() - 0.5) * MAX_Y_POSITION * SimulationPanel.SCALE,
                                                   (random.nextDouble() - 0.5) * MAX_X_VELOCITY * SimulationPanel.SCALE,
                                                   (random.nextDouble() - 0.5) * MAX_Y_VELOCITY * SimulationPanel.SCALE, 0.0, 0.0));
                }
            }
        });
        return button;
    }
}
