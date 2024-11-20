package ui;

import model.Simulation;

public class RemoveObjectWindow extends ButtonWindow{

    public RemoveObjectWindow(Simulation simulation) {
        super("Remove Object", 300, 200, simulation);
    }

    @Override
    protected void initializeElements() {
        initializeButtons(1);
    }

    @Override
    protected void buttonAction(Integer i) {
        simulation.removeObject(i);
    }
}
