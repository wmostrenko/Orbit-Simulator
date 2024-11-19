package ui;

import model.Simulation;
import model.Object;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;

public class AddObjectWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Simulation simulation;

    private ArrayList<String> labels;
    private ArrayList<JTextField> textFields;
    private JTextField massTextField;
    private JTextField xPositionTextField;
    private JTextField yPositionTextField;
    private JTextField xVelocityTextField;
    private JTextField yVelocityTextField;

    private static int WHITESPACE = 7;

    public AddObjectWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeTextFields();
        initializeLabels();
        initializeFrame();
    }

    private void initializeLabels() {
        labels = new ArrayList<String>();
        labels.add("Mass (SM): ");
        labels.add("X Position (AU): ");
        labels.add("Y Position (AU): ");
        labels.add("X Velocity (AU/yr): ");
        labels.add("Y Velocity (AU/yr): ");
    }

    private void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        
        massTextField = new JTextField("0.0");
        textFields.add(massTextField);
        xPositionTextField = new JTextField("0.0");
        textFields.add(xPositionTextField);
        yPositionTextField = new JTextField("0.0");
        textFields.add(yPositionTextField);
        xVelocityTextField = new JTextField("0.0");
        textFields.add(xVelocityTextField);
        yVelocityTextField = new JTextField("0.0");
        textFields.add(yVelocityTextField);
    }

    private void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Add Object");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 200);
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
        mainPanel.add(createPanel(2));
        mainPanel.add(createPanel(3));
        mainPanel.add(createPanel(4));
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
        JButton addObjectButton = createAddObjectButton();
        mainPanel.add(addObjectButton);
    }

    private JButton createAddObjectButton() {
        JButton button = new JButton("Add Object");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.addObject(new Object(Double.parseDouble(textFields.get(0).getText()),
                                                Double.parseDouble(textFields.get(1).getText()),
                                                Double.parseDouble(textFields.get(2).getText()),
                                                Double.parseDouble(textFields.get(3).getText()),
                                                Double.parseDouble(textFields.get(4).getText()),0, 0));
            }
        });
        return button;
    }
}
