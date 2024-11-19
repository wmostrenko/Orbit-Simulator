package ui;

import model.Simulation;
import persistence.JsonReader;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadSimulationWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Simulation simulation;
    private Simulation newSimulation;
    private JTextField textField;

    private static int WHITESPACE = 7;

    public LoadSimulationWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    public void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Load Simulation");
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
        initializeSimulationNameButton();
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

        JLabel label = new JLabel("Simulation Name: ");
        panel.add(label);

        textField = new JTextField(simulation.getName());
        panel.add(textField);
        
        return panel;
    }

    private void initializeSimulationNameButton() {
        JButton simulationNameButton = createSimulationNameButton();
        mainPanel.add(simulationNameButton);
    }

    private JButton createSimulationNameButton() {
        JButton button = new JButton("Load Simulation");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader jsonReader = new JsonReader("./data/" + textField.getText() + ".json");
                try {
                    newSimulation = jsonReader.read();
                    new MainWindow(newSimulation);
                } catch (IOException e1) {
                    System.out.println("That name doesn't exist!");
                }
            }
        });
        return button;
    }
}
