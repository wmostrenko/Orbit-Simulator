package ui;

import model.Simulation;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
