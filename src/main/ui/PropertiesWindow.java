package ui;

import model.Object;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

import java.util.ArrayList;

public class PropertiesWindow extends SubWindow {
    private ArrayList<JLabel> staticLabels;
    private ArrayList<JLabel> propertyLabels;

    public PropertiesWindow(Object object) {
        super("Properties", 300, 200, object);
    }

    @Override
    protected void initializeElements() {
        initializeStaticLabels();
        initializePropertyLabels();
        initializePanels();
    }

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

    private void initializePanels() {
        for (int i = 0; i < 7; i++) {
            mainPanel.add(createPanel(i));
        }
    }

    private JPanel createPanel(Integer i) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);
        panel.add(staticLabels.get(i));
        panel.add(propertyLabels.get(i));
        return panel;
    }
}
