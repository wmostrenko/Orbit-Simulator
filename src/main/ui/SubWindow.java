package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Simulation;
import model.Object;

/*
 * Represents a window for which either information is 
 * displayed or the user interacts with their simulation.
 */
public abstract class SubWindow {
    private static int WHITESPACE = 7;

    private JFrame frame;
    protected JPanel mainPanel;
    protected String title;
    private Integer width;
    private Integer height;
    protected Simulation simulation;
    protected Object object;

    /**
     * EFFECTS: Constructs a window for which either
     * information is displayed or the user interacts
     * with their simulation.
     * 
     * @param title title of the window.
     * @param width width of the window.
     * @param height height of the window.
     * @param simulation is the Simulation modified via a SubWindow.
     */
    public SubWindow(String title, Integer width,
                     Integer height, Simulation simulation) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.simulation = simulation;
        initializeFrame();
    }

    /**
     * EFFECTS: Constructs a window for which either
     * information is displayed or the user interacts
     * without a specified simulation.
     * 
     * @param title title of the window.
     * @param width width of the window.
     * @param height height of the window.
     */
    public SubWindow(String title, Integer width,
                     Integer height) {
        this.title = title;
        this.width = width;
        this.height = height;
        initializeFrame();
    }

    /**
     * EFFECTS: Constructs a window for which either
     * information is displayed or the user interacts
     * with an object.
     * 
     * @param title title of the window.
     * @param width width of the window.
     * @param height height of the window.
     * @param object is the Object primarily used via
     * a SubWindow.
     */
    public SubWindow(String title, Integer width,
                     Integer height, Object object) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.object = object;
        initializeFrame();
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates a new window with height and width.
     */
    public void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle(title);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(width, height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        initializeMainPanel();
        this.frame.setVisible(true);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates the main panel for which other panels
     * are added.
     */
    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 0));
        mainPanel.setBackground(Color.WHITE);
        initializeElements();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));
        frame.add(mainPanel);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Method for initializing and added objects to the
     * mainPanel.
     */
    protected abstract void initializeElements();
}
