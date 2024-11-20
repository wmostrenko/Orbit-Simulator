package ui;

import model.Simulation;

/*
 * Represents a window for which users can get the properties for a specified
 * object in their simulation.
 */
public class GetPropertiesWindow extends ButtonWindow {
    /**
     * EFFECTS: Constructs a window for which users can get the properties 
     * for a specified object in their simulation.
     * 
     * @param simulation Simulation for which users will observe objects.
     */
    public GetPropertiesWindow(Simulation simulation) {
        super("Get Properties Window", 300, 200, simulation);
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
     * EFFECTS: Opens a new PropertiesWindow for the object whose associated
     * button was pressed.
     */
    @Override
    protected void buttonAction(Integer i) {
        new PropertiesWindow(simulation.getObjectAt(i));
    }
}
