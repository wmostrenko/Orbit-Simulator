package ui;

import model.Simulation;

import javax.swing.*;

import java.util.ArrayList;

/*
 * Represents a window for which users can change the time step of
 * their simulation.
 */
public class ChangeTimeStepWindow extends TextAndButtonWindow {
    /**
     * EFFECTS: Constructs an window for which users can change the time
     * step of their simulation.
     * 
     * @param simulation Simulation for which users will change the time
     * step.
     */
    public ChangeTimeStepWindow(Simulation simulation) {
        super("Change Time Step", 300, 100, simulation);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified labels to labels.
     */
    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Time Step: "));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified textFields to textFields.
     */
    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField(Double.toString(simulation.getTimeStep())));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Changes time step of simulation according to time step specified.
     */
    @Override
    protected void buttonAction() {
        simulation.changeTimeStep(Double.parseDouble(textFields.get(0).getText()));
    }
}
