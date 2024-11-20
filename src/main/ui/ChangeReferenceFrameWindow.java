package ui;

import model.Simulation;

public class ChangeReferenceFrameWindow extends ButtonWindow {

    public ChangeReferenceFrameWindow(Simulation simulation) {
        super("Change Reference Frame", 300, 200, simulation);
    }

    @Override
    protected void initializeElements() {
        initializeButtons(0);
    }

    @Override
    protected void buttonAction(Integer i) {
        simulation.setCurrentReferenceFrame(simulation.getObjectAt(i));
    }
}
