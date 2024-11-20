package ui;

import model.Simulation;

/*
 * Represents a window for which users can change the reference frame of
 * their simulation.
 */
public class ChangeReferenceFrameWindow extends ButtonWindow {
    /**
     * EFFECTS: Constructs a window for which users can change the
     * reference frame of their simulation.
     * 
     * @param simulation is the Simulation whose reference frame the
     * users will change.
     */
    public ChangeReferenceFrameWindow(Simulation simulation) {
        super("Change Reference Frame", 300, 200, simulation);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Creates buttons corresponding to each Object in simulation.
     */
    @Override
    protected void initializeElements() {
        initializeButtons(0);
    }

    /*
     * MODIFIES: super
     * EFFECTS: Sets current reference frame of simulation to the Object
     * whose associated button was pressed.
     */
    @Override
    protected void buttonAction(Integer i) {
        simulation.setCurrentReferenceFrame(simulation.getObjectAt(i));
    }
}
