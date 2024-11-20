package ui;

import model.Simulation;
import persistence.JsonReader;

import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;

public class LoadSimulationWindow extends TextAndButtonWindow {

    public LoadSimulationWindow(Simulation simulation) {
        super("Load Simulation", 300, 100, simulation);
    }

    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Simulation Name: "));
    }

    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField(simulation.getName()));
    }

    @Override
    protected void buttonAction() {
        JsonReader jsonReader = new JsonReader("./data/" + textFields.get(0).getText() + ".json");
        try {
            new MainWindow(jsonReader.read());
        } catch (IOException e1) {
            System.out.println("That name doesn't exist!");
        }
    }
}
