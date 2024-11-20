package ui;

import model.Simulation;
import model.Object;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class AddRandomObjectsWindow extends TextAndButtonWindow {
    private static int MAX_MASS = 100;
    private static int MAX_X_POSITION = 700;
    private static int MAX_Y_POSITION = 700;
    private static int MAX_X_VELOCITY = 20;
    private static int MAX_Y_VELOCITY = 20;

    public AddRandomObjectsWindow(Simulation simulation) {
        super("Add Random Objects", 300, 100, simulation);
    }

    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Number of Objects: "));
    }

    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField("0"));
    }

    @Override
    protected void buttonAction() {
        Random random = new Random();
        for (int i = 0; i < Double.parseDouble(textFields.get(0).getText()); i++) {
            simulation.addObject(new Object(random.nextDouble() * MAX_MASS * SimulationPanel.SCALE,
                                            (random.nextDouble() - 0.5) * MAX_X_POSITION * SimulationPanel.SCALE,
                                            (random.nextDouble() - 0.5) * MAX_Y_POSITION * SimulationPanel.SCALE,
                                            (random.nextDouble() - 0.5) * MAX_X_VELOCITY * SimulationPanel.SCALE,
                                            (random.nextDouble() - 0.5) * MAX_Y_VELOCITY * SimulationPanel.SCALE, 0.0, 0.0));
        }
    }
}