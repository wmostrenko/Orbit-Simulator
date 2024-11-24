package ui;

import model.Simulation;
import persistence.JsonWriter;
import logs.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.Iterator;

/*
 * Represents the main window of the application with a widget
 * bar, tool bar, and simulation window.
 */
public class MainWindow {
    private static final int INTERVAL = 1;

    private JFrame frame;
    private SimulationPanel simulationPanel;
    private JPanel widgetPanel;
    private JPanel toolPanel;
    private JPanel commandPanel;
    private Simulation simulation;
    private JsonWriter jsonWriter;

    /**
     * EFFECTS: Constructs the main window of the applicaiton
     * with a tool bar, command bar, and simulation window.
     * 
     * @param simulation is the Simulation that will be displayed.
     */
    public MainWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Constructs the actual main window.
     */
    private void initializeFrame() {
        frame = new JFrame();
        frame.setTitle(simulation.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        initializePanels();
        frame.setVisible(true);
        addTimer();
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {
                @SuppressWarnings("rawtypes")
                Iterator it = EventLog.getInstance().iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
            }

            @Override
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }   
        });
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Adds the widget panels and simulation panel to 
     * the main frame.
     */
    private void initializePanels() {
        initializeWidgetPanel();
        simulationPanel = new SimulationPanel(simulation);
        frame.add(simulationPanel);
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Creates the widget panels and adds them to the
     * main frame.
     */
    private void initializeWidgetPanel() {
        widgetPanel = new JPanel();
        widgetPanel.setLayout(new GridLayout(2, 1));
        widgetPanel.setBackground(Color.WHITE);
        widgetPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        initializeToolPanel();
        initializeCommandPanel();
        frame.add(widgetPanel, BorderLayout.EAST);
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Constructs tool panel and adds it to the widget
     * panel.
     */
    private void initializeToolPanel() {
        toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(0, 1));
        toolPanel.setBackground(Color.WHITE);
        createToolButtons();
        widgetPanel.add(toolPanel, BorderLayout.NORTH);
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Creates the buttons for each tool and adds them to
     * the tool panel.
     */
    private void createToolButtons() {
        toolPanel.add(createAddObjectButton());
        toolPanel.add(createAddRandomObjectsButton());
        toolPanel.add(createRemoveObjectButton());
        toolPanel.add(createGetPropertiesButton());
        toolPanel.add(createChangeReferenceFrameButton());
        toolPanel.add(createChangeTimeStepButton());
        toolPanel.add(createChangeNameButton());
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open an AddObjectWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open an AddRandomObjectsWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a RemoveObjectWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a GetPropertiesWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a ChangeReferenceFrameWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a ChangeTimeStepWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a ChangeNameWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Constructs command panel and adds it to the widget
     * panel.
     */
    private void initializeCommandPanel() {
        commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(0, 1));
        commandPanel.setBackground(Color.WHITE);
        commandPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        createCommandButtons();
        widgetPanel.add(commandPanel, BorderLayout.SOUTH);
    }
    
    /*
     * MODIFIES: this.
     * EFFECTS: Creates the buttons for each command and adds them to
     * the command panel.
     */
    private void createCommandButtons() {
        commandPanel.add(createSaveSimulationButton());
        commandPanel.add(createLoadSimulationButton());
        commandPanel.add(createNewSimulationButton());
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to save the current simulation.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Saves simulation to a file with the name, 
     * <simulationName>.json.
     */
    private void saveSimulation() {
        try {
            jsonWriter = new JsonWriter("./data/" + simulation.getName() + ".json");
            jsonWriter.open();
            jsonWriter.write(simulation);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file \"" + simulation.getName() + "\".");
        }
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a LoadSimulationWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a button to open a NewSimulationWindow.
     */
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

    /*
     * MODIFIES: this.
     * EFFECTS: Creates a timer that ticks according to INTERVAL
     * that updates the objects in the simulation and repaints the
     * simulationPanel.
    */
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