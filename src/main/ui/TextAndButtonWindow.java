package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Simulation;

public abstract class TextAndButtonWindow extends SubWindow {
    protected ArrayList<JLabel> labels;
    protected ArrayList<JTextField> textFields;

    public TextAndButtonWindow(String title, Integer width, Integer height, Simulation simulation) {
        super(title, width, height, simulation);
    }

    public TextAndButtonWindow(String title, Integer width, Integer height) {
        super(title, width, height);
    }

    @Override
    public void initializeElements() {
        initializeLabels();
        initializeTextFields();
        initializeFieldPanels();
        initializeButton();
    }

    protected abstract void initializeLabels();

    protected abstract void initializeTextFields();

    private void initializeFieldPanels() {
        for (int i = 0; i < labels.size(); i++) {
            mainPanel.add(createPanel(i));
        }
    }

    private JPanel createPanel(Integer i) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);
        panel.add(labels.get(i));
        panel.add(textFields.get(i));
        return panel;
    }

    private void initializeButton() {
        mainPanel.add(createButton());
    }

    private JButton createButton() {
        JButton button = new JButton(title);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction();
            }
        });
        return button;
    }

    protected abstract void buttonAction();
}
