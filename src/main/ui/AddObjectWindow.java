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

    private ArrayList<Double> values;
    private ArrayList<String> labels;

    private static int WHITESPACE = 7;

    public AddObjectWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeValues();
        initializeLabels();
        initializeFrame();
    }

    private void initializeValues() {
        values = new ArrayList<Double>();
        for (int i = 0; i < 5; i++) {
            values.add(0.0);
        }
    };

    private void initializeLabels() {
        labels = new ArrayList<String>();
        labels.add("Mass (SM): ");
        labels.add("X Position (AU): ");
        labels.add("Y Position (AU): ");
        labels.add("X Velocity (AU/yr): ");
        labels.add("Y Velocity (AU/yr): ");
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

        JTextField textField = createTextField(index);
        panel.add(textField);
        
        return panel;
    }

    private JTextField createTextField(Integer index) {
        JTextField textField = new JTextField(Double.toString(values.get(index)));
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                values.set(index, Double.parseDouble(textField.getText()));
            }
        });
        return textField;
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
                simulation.addObject(new Object(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), 0, 0));
            }
        });
        return button;
    }
}
