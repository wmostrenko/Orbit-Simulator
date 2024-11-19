package ui;

import model.Object;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

import java.util.ArrayList;

public class PropertiesWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Object object;

    private ArrayList<JLabel> staticLabels;
    private ArrayList<JLabel> propertyLabels;

    private static int WHITESPACE = 7;

    public PropertiesWindow(Object object) {
        this.object = object;
        initializeStaticLabels();
        initializePropertyLabels();
        initializeFrame();
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

    private void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Add Object");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 200);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        initializeMainPanel();
        this.frame.setVisible(true);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 0));
        mainPanel.setBackground(Color.WHITE);
        initializePropertyPanels();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));

        frame.add(mainPanel);
    }

    private void initializePropertyPanels() {
        mainPanel.add(createPanel(0));
        mainPanel.add(createPanel(1));
        mainPanel.add(createPanel(2));
        mainPanel.add(createPanel(3));
        mainPanel.add(createPanel(4));
        mainPanel.add(createPanel(5));
        mainPanel.add(createPanel(6));
    }

    private JPanel createPanel(Integer index) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);
        panel.add(staticLabels.get(index));
        panel.add(propertyLabels.get(index));
        return panel;
    }
}
