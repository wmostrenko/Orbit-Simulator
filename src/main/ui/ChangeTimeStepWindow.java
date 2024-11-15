package ui;

import model.Simulation;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;

public class ChangeTimeStepWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Simulation simulation;
    private double timeStep;

    private static int WHITESPACE = 7;

    public ChangeTimeStepWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    public void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Change Time Step");
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
        initializeChangeTimeStepButton();
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

        JLabel label = new JLabel("Timestep: ");
        panel.add(label);

        JTextField textField = createTextField();
        panel.add(textField);
        
        return panel;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(Double.toString(simulation.getTimeStep()));
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeStep = Double.parseDouble(textField.getText());
            }
        });
        return textField;
    }

    private void initializeChangeTimeStepButton() {
        JButton changeTimeStepButton = createChangeTimeStepButton();
        mainPanel.add(changeTimeStepButton);
    }

    private JButton createChangeTimeStepButton() {
        JButton button = new JButton("Change Time Step");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.changeTimeStep(timeStep);
            }
        });
        return button;
    }
}
