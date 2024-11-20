package ui;

import model.Simulation;

public class GetPropertiesWindow extends ButtonWindow {

    public GetPropertiesWindow(Simulation simulation) {
        super("Get Properties Window", 300, 200, simulation);
    }

    @Override
    protected void initializeElements() {
        initializeButtons(0);
    }

    @Override
    protected void buttonAction(Integer i) {
        new PropertiesWindow(simulation.getObjectAt(i));
    }
}
