package ui;

import model.Simulation;

import javax.swing.*;

import java.util.ArrayList;

/*
 * Represents a window for which users can add change the name of their
 * simulation.
 */
public class ChangeNameWindow extends TextAndButtonWindow {
    /**
     * EFFECTS: Constructs a window for which users can change the
     * simulation name.
     * 
     * @param simulation is the Simulation whose name the users will
     * change.
     */
    public ChangeNameWindow(Simulation simulation) {
        super("Change Name", 300, 100, simulation);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified labels to labels.
     */
    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Name: "));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified textFields to textFields.
     */
    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField(simulation.getName()));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Changes name of simulation to text from the textField.
     */
    @Override
    protected void buttonAction() {
        simulation.changeName(textFields.get(0).getText());
    }
}
