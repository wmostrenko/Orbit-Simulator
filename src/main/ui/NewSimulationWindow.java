package ui;

import model.Simulation;
import model.Object;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;

public class NewSimulationWindow {
    private JFrame frame;
    private JPanel mainPanel;

    private ArrayList<String> labels;
    private ArrayList<JTextField> textFields;

    private static int WHITESPACE = 7;

    public NewSimulationWindow() {
        initializeTextFields();
        initializeLabels();
        initializeFrame();
    }

    private void initializeLabels() {
        labels = new ArrayList<String>();
        labels.add("Name: ");
        labels.add("Time Step: ");
    }

    private void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField("untitled"));
        textFields.add(new JTextField("0.1"));
    }

    private void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("New Simulation");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 125);
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
        initializeAddObjectButton();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));

        frame.add(mainPanel);
    }

    private void initializeFieldPanels() {
        mainPanel.add(createPanel(0));
        mainPanel.add(createPanel(1));
    }

    private JPanel createPanel(Integer index) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labels.get(index));
        panel.add(label);

        panel.add(textFields.get(index));
        
        return panel;
    }

    private void initializeAddObjectButton() {
        JButton newSimulationButton = createNewSimulationButton();
        mainPanel.add(newSimulationButton);
    }

    private JButton createNewSimulationButton() {
        JButton button = new JButton("Create Simulation");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainWindow(new Simulation(textFields.get(0).getText(),
                                              Double.parseDouble(textFields.get(1).getText())));
            }
        });
        return button;
    }
}
