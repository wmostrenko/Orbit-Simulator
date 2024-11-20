package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Simulation;
import model.Object;

public abstract class SubWindow {
    private static int WHITESPACE = 7;

    private JFrame frame;
    protected JPanel mainPanel;
    protected String title;
    private Integer width;
    private Integer height;
    protected Simulation simulation;
    protected Object object;

    public SubWindow(String title, Integer width, Integer height, Simulation simulation) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.simulation = simulation;
        initializeFrame();
    }

    public SubWindow(String title, Integer width, Integer height) {
        this.title = title;
        this.width = width;
        this.height = height;
        initializeFrame();
    }

    public SubWindow(String title, Integer width, Integer height, Object object) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.object = object;
        initializeFrame();
    }

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

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 0));
        mainPanel.setBackground(Color.WHITE);
        initializeElements();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));
        frame.add(mainPanel);
    }

    protected abstract void initializeElements();
}
