package ui;

import model.Simulation;

/*
 * Represents a window for which users can remove an object from
 * their simulation.
 */
public class RemoveObjectWindow extends ButtonWindow{
    /**
     * EFFECTS: Constructs a window for which users can remove an
     * object from their simulation.
     * 
     * @param simulation is the Simulation for which an object will
     * be removed.
     */
    public RemoveObjectWindow(Simulation simulation) {
        super("Remove Object", 300, 200, simulation);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates buttons corresponding to each Object in simulation
     * (excluding the stationary reference frame).
     */
    @Override
    protected void initializeElements() {
        initializeButtons(1);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Removes object from simulation whose assoicated button
     * was pressed.
     */
    @Override
    protected void buttonAction(Integer i) {
        simulation.removeObject(i);
    }
}
