package ui;

import model.Simulation;
import model.Object;
import java.util.ArrayList;
import javax.swing.*;

/*
 * Represents a window for which users can add objects to their simulation.
 */
public class AddObjectWindow extends TextAndButtonWindow {
    /**
     * EFFECTS: Constructs an window for which users can add objects.
     * 
     * @param simulation Simulation for which users will add objects.
     */
    public AddObjectWindow(Simulation simulation) {
        super("Add Object", 300, 200, simulation);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified labels to labels.
     */
    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Mass (SM): "));
        labels.add(new JLabel("X Position (AU): "));
        labels.add(new JLabel("Y Position (AU): "));
        labels.add(new JLabel("X Velocity (AU/yr): "));
        labels.add(new JLabel("Y Velocity (AU/yr): "));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified textFields to labels.
     */
    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField("0.0"));
        textFields.add(new JTextField("0.0"));
        textFields.add(new JTextField("0.0"));
        textFields.add(new JTextField("0.0"));
        textFields.add(new JTextField("0.0"));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds object to simulation accortding to responses in textFields.
     */
    @Override
    protected void buttonAction() {
        simulation.addObject(new Object(Double.parseDouble(textFields.get(0).getText()),
                                        Double.parseDouble(textFields.get(1).getText()),
                                        Double.parseDouble(textFields.get(2).getText()),
                                        Double.parseDouble(textFields.get(3).getText()),
                                        Double.parseDouble(textFields.get(4).getText()),0, 0));
    }
}
