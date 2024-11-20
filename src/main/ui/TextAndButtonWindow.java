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

/*
 * Represents a window for which a series of labels and
 * associated text fields are displayed, with a button
 * at the bottom to do some action.
 */
public abstract class TextAndButtonWindow extends SubWindow {
    protected ArrayList<JLabel> labels;
    protected ArrayList<JTextField> textFields;

    /**
     * EFFECTS: Constructs a window for which a series of
     * labels and associated text fields are displayed,
     * with a button at the bottom to do some action.
     * 
     * @param title      title of the window.
     * @param width      width of the window.
     * @param height     height of the window.
     * @param simulation is the Simulation modified via a
     *                   SubWindow.
     */
    public TextAndButtonWindow(String title, Integer width, Integer height, Simulation simulation) {
        super(title, width, height, simulation);
    }

    /**
     * EFFECTS: Constructs a window for which a series of
     * labels and associated text fields are displayed,
     * with a button at the bottom to do some action.
     * 
     * @param title      title of the window.
     * @param width      width of the window.
     * @param height     height of the window.
     */
    public TextAndButtonWindow(String title, Integer width, Integer height) {
        super(title, width, height);
    }

    /*
     * EFFECTS: initializes labels, textFields, fieldPanels, and buttons.
     */
    @Override
    public void initializeElements() {
        initializeLabels();
        initializeTextFields();
        initializePanels();
        initializeButton();
    }

    /*
     * EFFECTS: Creates labels and adds them the labels.
     */
    protected abstract void initializeLabels();

    /*
     * EFFECTS: Creates textFields and adds them the textFields.
     */
    protected abstract void initializeTextFields();

    /*
     * MODIFIES: super
     * EFFECTS: Creates pannels which contains a static label and
     * its associated property label. Adds them to the mainPanel.
     */    
    private void initializePanels() {
        for (int i = 0; i < labels.size(); i++) {
            mainPanel.add(createPanel(i));
        }
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates a pannel which contains a label and
     * its associated text field.
     */
    private JPanel createPanel(Integer i) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);
        panel.add(labels.get(i));
        panel.add(textFields.get(i));
        return panel;
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates a button and adds it to the mainPanel.
     */
    private void initializeButton() {
        mainPanel.add(createButton());
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates a button with a specified action.
     */
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

    /*
     * EFFECTS: Specifies the action for which a button does.
     */
    protected abstract void buttonAction();
}
