package ui;

import model.Simulation;

import javax.swing.*;

import java.util.ArrayList;

public class ChangeTimeStepWindow extends TextAndButtonWindow {

    public ChangeTimeStepWindow(Simulation simulation) {
        super("Change Time Step", 300, 100, simulation);
    }

    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Time Step: "));
    }

    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField(Double.toString(simulation.getTimeStep())));
    }

    @Override
    protected void buttonAction() {
        simulation.changeTimeStep(Double.parseDouble(textFields.get(0).getText()));
    }
}
