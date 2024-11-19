package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Simulation;
import model.Object;

public class SimulationPanel extends JPanel {
    private Simulation simulation;
    public static Double SCALE = 100.0;

    public SimulationPanel(Simulation simulation) {
        super();
        setBackground(Color.WHITE);
        this.simulation = simulation;
    }

    @Override
	protected void paintComponent(Graphics graphics) { 
		super.paintComponent(graphics);
		drawObjects(graphics);
	}

    private void drawObjects(Graphics graphics) {
        for (int i = 1; i < simulation.getNumberOfObjects(); i++) {
            drawObject(graphics, simulation.getObjectAt(i));
        }
    }

    private void drawObject(Graphics graphics, Object object) {
        Color savedCol = graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.fillOval((int) (this.getSize().getWidth()/2 + object.getXPosition()/SCALE),
                          (int) (this.getSize().getHeight()/2 - object.getYPosition()/SCALE), 5, 5);
		graphics.setColor(savedCol);
    }
}