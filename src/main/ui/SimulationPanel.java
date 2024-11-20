package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Simulation;
import model.Object;

/*
 * Represents the panel that the Simulation is displayed in.
 */
public class SimulationPanel extends JPanel {
    private Simulation simulation;
    public static Double SCALE = 10.0;

    /**
     * EFFECTS: Constructs a panel for which objects in the
     * simulation will be displayed.
     * 
     * @param simulation is the Simulation that will be displayed.
     */
    public SimulationPanel(Simulation simulation) {
        super();
        setBackground(Color.WHITE);
        this.simulation = simulation;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Redraws this.
     */
    @Override
	protected void paintComponent(Graphics graphics) { 
		super.paintComponent(graphics);
		drawObjects(graphics);
	}

    /*
     * MODIFIES: this
     * EFFECTS: Redraws all objects in the Simulation on this.
     */
    private void drawObjects(Graphics graphics) {
        for (int i = 1; i < simulation.getNumberOfObjects(); i++) {
            drawObject(graphics, simulation.getObjectAt(i));
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Redraws a single object in the Simulation on this.
     */
    private void drawObject(Graphics graphics, Object object) {
        Color savedCol = graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.fillOval((int) (this.getSize().getWidth()/2 
                                 + object.getXPosition()/SCALE),
                          (int) (this.getSize().getHeight()/2 
                                 - object.getYPosition()/SCALE), 5, 5);
		graphics.setColor(savedCol);
    }
}