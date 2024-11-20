package ui;

import model.Simulation;

import java.util.ArrayList;

import javax.swing.*;

public class NewSimulationWindow extends TextAndButtonWindow {

    public NewSimulationWindow() {
        super("Create New Simulation", 300, 125);
    }

    @Override
    protected void initializeLabels() {
        labels = new ArrayList<JLabel>();
        labels.add(new JLabel("Simulation Name: "));
        labels.add(new JLabel("Time Step: "));
    }

    @Override
    protected void initializeTextFields() {
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField("untitled"));
        textFields.add(new JTextField("0.01"));
    }

    @Override
    protected void buttonAction() {
        new MainWindow(new Simulation(textFields.get(0).getText(),
                                      Double.parseDouble(textFields.get(1).getText())));
    }
}
