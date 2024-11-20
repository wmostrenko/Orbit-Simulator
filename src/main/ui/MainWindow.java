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
    private static final int INTERVAL = 1;
    private JFrame frame;
    private SimulationPanel simulationPanel;
    private JPanel widgetPanel;
    private JPanel toolPanel;
    private JPanel commandPanel;

    private Simulation simulation;

    private JsonWriter jsonWriter;

    public MainWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setTitle(simulation.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        initializePanels();
        frame.setVisible(true);
        addTimer();
    }

    private void initializePanels() {
        initializeWidgetPanel();
        simulationPanel = new SimulationPanel(simulation);
        frame.add(simulationPanel);
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
        toolPanel.add(createAddRandomObjectsButton());
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

    private JButton createAddRandomObjectsButton() {
        JButton button = new JButton("Add Random Objects");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRandomObjectsWindow(simulation);
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
                new RemoveObjectWindow(simulation);
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
                new GetPropertiesWindow(simulation);
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
                new ChangeReferenceFrameWindow(simulation);
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
                new LoadSimulationWindow();
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
                new NewSimulationWindow();
            }
        });
        return button;
    }

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

    private void addTimer() {
		Timer t = new Timer(INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				simulation.updateObjects();
                simulationPanel.repaint();
			}
		});
		t.start();
	}
}