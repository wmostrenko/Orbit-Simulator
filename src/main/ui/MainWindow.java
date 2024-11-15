package ui;

import model.Simulation;

import persistence.JsonWriter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MainWindow {
    private JFrame frame;
    private JPanel simulationPanel;
    private JPanel widgetPanel;
    private JPanel toolPanel;
    private JPanel commandPanel;

    private Simulation simulation;

    private JsonWriter jsonWriter;

    public MainWindow() {
        simulation = defaultSimulation();
        initializeFrame();
    }

    public Simulation defaultSimulation() {
        return new Simulation("untitled", 0.1);
    }

    private void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle(simulation.getName());
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(900,600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false); // TODO: Add functionality to resize
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
        widgetPanel.setBackground(Color.WHITE);
        widgetPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        initializeToolPanel();
        initializeCommandPanel();
        frame.add(widgetPanel, BorderLayout.EAST);
    }

    private void initializeToolPanel() {
        toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(0, 1));
        toolPanel.setBackground(Color.WHITE);
        createToolButtons();
        widgetPanel.add(toolPanel, BorderLayout.NORTH);
    }

    private void createToolButtons() {
        toolPanel.add(createAddObjectButton());
        toolPanel.add(createRemoveObjectButton());
        toolPanel.add(createGetPropertiesButton());
        toolPanel.add(createChangeReferenceFrameButton());
        toolPanel.add(createChangeTimeStepButton());
        toolPanel.add(createChangeNameButton());
    }

    private JButton createAddObjectButton() {
        JButton button = new JButton("Add Object");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddObjectWindow(simulation);
            }
        });
        return button;
    }

    private JButton createRemoveObjectButton() {
        JButton button = new JButton("Remove Object");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Remove object
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
                System.out.println("Get Properties Placeholder"); // TODO: Get Properties
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
                System.out.println("Change Reference Frame Placeholder"); // TODO: Change Reference Frame
            }
        });
        return button;
    }

    private JButton createChangeTimeStepButton() {
        JButton button = new JButton("Change Time Step");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangeTimeStepWindow(simulation);
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
                new ChangeNameWindow(simulation);
            }
        });
        return button;
    }

    private void initializeCommandPanel() {
        commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(0, 1));
        commandPanel.setBackground(Color.WHITE);
        commandPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        createCommandButtons();
        widgetPanel.add(commandPanel, BorderLayout.SOUTH);
    }
    
    private void createCommandButtons() {
        commandPanel.add(createSaveSimulationButton());
        commandPanel.add(createLoadSimulationButton());
        commandPanel.add(createNewSimulationButton());
    }

    private JButton createSaveSimulationButton() {
        JButton button = new JButton("Save Simulation");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSimulation();
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
                System.out.println("Load Simulation Placeholder"); // TODO: Load Simulatioin
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
                System.out.println("New Simulation Placeholder"); // TODO: New Simulation
            }
        });
        return button;
    }

    private void initializeSimulationPanel() {
        simulationPanel = new JPanel();
        simulationPanel.setBackground(Color.WHITE);
        frame.add(simulationPanel);
    }

    /*
     * EFFECTS: saves current simulation to a file.
     */
    private void saveSimulation() {
        try {
            jsonWriter = new JsonWriter("./data/" + simulation.getName() + ".json");
            jsonWriter.open();
            jsonWriter.write(simulation);
            jsonWriter.close();
            System.out.println("Simulation saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file \"" + simulation.getName() + "\".");
        }
    }
}
