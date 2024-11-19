package ui;

import model.Simulation;

public class Main {
    public static void main(String[] args) throws Exception {
        new MainWindow(new Simulation("untitled", 0.05));
    }
}
