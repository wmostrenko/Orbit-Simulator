package ui;

import model.Simulation;

import java.util.ArrayList;

import javax.swing.*;

/*
 * Represents a window for which users can add a user-specified number of randomly
 * generated objects to their simulation.
 */
public class NewSimulationWindow extends TextAndButtonWindow {
    /**
     * EFFECTS: Constructs a window for which users can create a new simulation
     * with a name and time step.
     */
    public NewSimulationWindow() {
        super("Create New Simulation", 300, 125);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified labels to labels.
     */
    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Simulation Name: "));
        labels.add(new JLabel("Time Step: "));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified textFields to textFields.
     */
    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField("untitled"));
        textFields.add(new JTextField("0.01"));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Opens a new MainWindow with a new simulation whose name
     * and time step was specified in the text fields by the user.
     */
    @Override
    protected void buttonAction() {
        new MainWindow(new Simulation(textFields.get(0).getText(),
                                      Double.parseDouble(textFields.get(1).getText())));
    }
}
