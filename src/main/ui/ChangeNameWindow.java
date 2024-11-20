package ui;

import model.Simulation;

import javax.swing.*;

import java.util.ArrayList;

public class ChangeNameWindow extends TextAndButtonWindow {
    
    public ChangeNameWindow(Simulation simulation) {
        super("Change Name", 300, 100, simulation);
    }

    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Name: "));
    }

    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField(simulation.getName()));
    }

    @Override
    protected void buttonAction() {
        simulation.changeName(textFields.get(0).getText());
    }
}
