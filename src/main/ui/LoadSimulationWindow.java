package ui;

import persistence.JsonReader;

import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Represents a window for which users can chose a previously saved simulation
 * to open.
 */
public class LoadSimulationWindow extends TextAndButtonWindow {
    /**
     * EFFECTS: Constructs a window for which users can chose a previously
     * saved simulation to open.
     */
    public LoadSimulationWindow() {
        super("Load Simulation", 300, 100);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Adds specified labels to labels.
     */
    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Simulation Name: "));
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
     * EFFECTS: Opens a new MainWindow with the simulation name specified
     * in the text field.
     */
    @Override
    protected void buttonAction() {
        JsonReader jsonReader = new JsonReader("./data/" +
                                               textFields.get(0).getText() +
                                               ".json");
        try {
            new MainWindow(jsonReader.read());
        } catch (IOException e1) {
            System.out.println("That name doesn't exist!");
        }
    }
}
