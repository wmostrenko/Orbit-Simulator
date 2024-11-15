package ui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JFrame frame;
    private JPanel simulationPanel;
    private JPanel widgetPanel;
    private JPanel toolPanel;
    private JPanel commandPanel;

    public MainWindow() {
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Simulation"); // TODO: Make simulation name
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(900,600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false); // TODO: Add functionality to resize eventually
        initializePanels();
        this.frame.setVisible(true);
    }

    private void initializePanels() {
        initializeWidgetPanel();
        initializeSimulationPanel();
    }

    private void initializeWidgetPanel() {
        widgetPanel = new JPanel();
        widgetPanel.setLayout(new GridLayout(2, 1));
        widgetPanel.setBackground(Color.GREEN);
        initializeToolPanel();
        initializeCommandPanel();
        frame.add(widgetPanel, BorderLayout.EAST);
    }

    private void initializeToolPanel() {
        toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(0, 1));
        toolPanel.setBackground(Color.ORANGE);
        createToolButtons();
        widgetPanel.add(toolPanel, BorderLayout.NORTH);
    }

    private void createToolButtons() {
        JButton addObjectButton = createAddObjectButton();
        toolPanel.add(addObjectButton);
        JButton getPropertiesButton = createGetPropertiesButton();
        toolPanel.add(getPropertiesButton);
        JButton changeReferenceFrameButton = createChangeReferenceFrameButton();
        toolPanel.add(changeReferenceFrameButton);
        JButton changeTimestepButton = createChangeTimestepButton();
        toolPanel.add(changeTimestepButton);
        JButton changeNameButton = createChangeNameButton();
        toolPanel.add(changeNameButton);
    }

    private JButton createAddObjectButton() {
        JButton button = new JButton("Add Object");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Object Placeholder"); // TODO: Open new window
            }
        });
        return button;
    }

    private JButton createGetPropertiesButton() {
        JButton button = new JButton("Get Properties");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Get Properties Placeholder"); // TODO: Open new window
            }
        });
        return button;
    }

    private JButton createChangeReferenceFrameButton() {
        JButton button = new JButton("Change Reference Frame");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Reference Frame Placeholder"); // TODO: Open new window
            }
        });
        return button;
    }

    private JButton createChangeTimestepButton() {
        JButton button = new JButton("Change Timestep");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Timestep"); // TODO: Open new window
            }
        });
        return button;
    }

    private JButton createChangeNameButton() {
        JButton button = new JButton("Change Name");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Name"); // TODO: Open new window
            }
        });
        return button;
    }

    private void initializeCommandPanel() {
        commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(0, 1));
        commandPanel.setBackground(Color.RED);
        createCommandButtons();
        widgetPanel.add(commandPanel, BorderLayout.SOUTH);
    }
    
    private void createCommandButtons() {
        JButton saveSimulationButton = createSaveSimulationButton();
        commandPanel.add(saveSimulationButton);
        JButton loadSimulationButton = createLoadSimulationButton();
        commandPanel.add(loadSimulationButton);
        JButton newSimulationButton = createNewSimulationButton();
        commandPanel.add(newSimulationButton);
    }

    private JButton createSaveSimulationButton() {
        JButton button = new JButton("Save Simulation");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Simulation Placeholder"); // TODO: Saves simulation
            }
        });
        return button;
    }

    private JButton createLoadSimulationButton() {
        JButton button = new JButton("Load Simulation");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load Simulation Placeholder"); // TODO: Opens new window
            }
        });
        return button;
    }

    private JButton createNewSimulationButton() {
        JButton button = new JButton("New Simulation");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Simulation Placeholder"); // TODO: Makes new simulation
            }
        });
        return button;
    }

    private void initializeSimulationPanel() {
        simulationPanel = new JPanel();
        simulationPanel.setBackground(Color.BLUE);
        frame.add(simulationPanel);
    }
}
