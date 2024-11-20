package ui;

import model.Object;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

import java.util.ArrayList;

/*
 * Represents a window with a list of properties associated with
 * object.
 */
public class PropertiesWindow extends SubWindow {
    private ArrayList<JLabel> staticLabels;
    private ArrayList<JLabel> propertyLabels;

    /**
     * EFFECTS: Constructs a window with a list of properties associated with
     * object.
     * 
     * @param object Object to display properties for.
     */
    public PropertiesWindow(Object object) {
        super("Properties", 300, 200, object);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Initializses staticLabels, propertyLabels, and panels containing
     * a static label and its associated property label.
     */
    @Override
    protected void initializeElements() {
        initializeStaticLabels();
        initializePropertyLabels();
        initializePanels();
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates static labels for which names of object
     * properties are assigned and adds them to staticLabels.
     */
    private void initializeStaticLabels() {
        staticLabels = new ArrayList<JLabel>();
        staticLabels.add(new JLabel("Mass (SM): "));
        staticLabels.add(new JLabel("X Position (AU): "));
        staticLabels.add(new JLabel("Y Position (AU): "));
        staticLabels.add(new JLabel("X Velocity (AU/yr): "));
        staticLabels.add(new JLabel("Y Velocity (AU/yr): "));
        staticLabels.add(new JLabel("X Acceleration (AU/yr^2): "));
        staticLabels.add(new JLabel("Y Acceleration (AU/yr^2): "));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates property labels for which object properties
     * are assigned and adds them to propertyLabels.
     */
    private void initializePropertyLabels() {
        propertyLabels = new ArrayList<JLabel>();
        propertyLabels.add(new JLabel(Double.toString(object.getMass())));
        propertyLabels.add(new JLabel(Double.toString(object.getXPosition())));
        propertyLabels.add(new JLabel(Double.toString(object.getYPosition())));
        propertyLabels.add(new JLabel(Double.toString(object.getXVelocity())));
        propertyLabels.add(new JLabel(Double.toString(object.getYVelocity())));
        propertyLabels.add(new JLabel(Double.toString(object.getXAcceleration())));
        propertyLabels.add(new JLabel(Double.toString(object.getYAcceleration())));
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates pannels which contains a static label and
     * its associated property label. Adds them to the mainPanel.
     */
    private void initializePanels() {
        for (int i = 0; i < 7; i++) {
            mainPanel.add(createPanel(i));
        }
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates a pannel which contains a static label and
     * its associated property label.
     */
    private JPanel createPanel(Integer i) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);
        panel.add(staticLabels.get(i));
        panel.add(propertyLabels.get(i));
        return panel;
    }
}
