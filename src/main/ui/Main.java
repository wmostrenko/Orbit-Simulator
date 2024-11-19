package ui;

import model.Simulation;

public class Main {
    public static void main(String[] args) throws Exception {
        // new SimulationApp();
        new MainWindow(new Simulation("untitled", 0.1));
    }
}
