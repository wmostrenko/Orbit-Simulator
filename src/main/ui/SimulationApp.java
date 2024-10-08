package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Simulation;

public class SimulationApp {
    ArrayList<Simulation> simulations = new ArrayList<Simulation>();
    private Simulation simulation; // dummy Simulation to add Simulation references to simulations
    private Scanner in = new Scanner(System.in);
    private double initialTimeStep; // user's initial timestep for new Simulations
    private int userIntInput; // user's selected index for choosing options from a list.

    public SimulationApp() {
        runSimulationApp();
    }

    public void runSimulationApp() {
        while (true) {
            System.out.println("Would you like to make a new simulation (type the number, 1), enter a previous one (type the number, 2), or exit the application (type the number, 3)?");

            // User selects new or existing simulation
            while (true) {
                userIntInput = in.nextInt();
                if ((userIntInput < 1) || (userIntInput > 3)) {
                    System.out.println("That isn't a valid option! Try again.");
                } else {
                    break;
                }
            }

            // Opens new interface depending on user's previous choice
            if (userIntInput == 1) {
                addSimulation();
            } else if (userIntInput == 2) {
                if (simulations.isEmpty()) {
                    System.out.println("You have yet to make any simulations! Please make a simulation to enter the simulator.");
                } else {
                    chooseExistingSimulation();
                }
            } else {
                break;
            }
        }
    }

    public void addSimulation() {
        simulation = null;
        System.out.println("What should be the initial timestep of the simulation (in yr). Note that this can always be changed later.");
        while (true) {
            initialTimeStep = in.nextDouble();
            if (initialTimeStep < 0) {
                System.out.println("Your timestep can't be negative! Try again.");
            } else {
                simulation = new Simulation(initialTimeStep);
                simulations.add(simulation);
                System.out.println("Your simulation has been created with an initial timestep of " + initialTimeStep);
                break;
            }
        }
    }

    public void chooseExistingSimulation() {
        System.out.println("Which simulation would you like to enter (#)?");
        for (int i = 0; i < simulations.size(); i++) {
            System.out.println(i + ". Simulation " + i);
        }

        while (true) {
            userIntInput = in.nextInt();
            if ((userIntInput >= simulations.size()) || (userIntInput < 0)) {
                System.out.println("That simulation isn't in your simulations! Try again.");
            } else {
                runSimulation(simulations.get(userIntInput));
                break;
            }
        }
    }

    public void runSimulation(Simulation simulation) {
        ObjectTool objectTool = new ObjectTool(simulation);
        while (true) {
            System.out.println("What would you like to do in your simulation?");
            System.out.println("1. Add an object.");
            System.out.println("2. Get an object's properties.");
            System.out.println("3. Update the simulation.");
            System.out.println("4. Close the simulation.");

            // Gets input from user
            while (true) {
                userIntInput = in.nextInt();
                if ((userIntInput < 1) || (userIntInput > 4)) {
                    System.out.println("That is an invalid option! Please try again.");
                } else {
                    break;
                } 
            }

            // Chooses option based on user's previous input
            if (userIntInput == 1) {
                objectTool.addObject();
            } else if (userIntInput == 2) {
                objectTool.getObjectProperties();
            } else if (userIntInput == 3) {
                simulation.standardUpdateObjects();
            } else {
                System.out.println("Exiting the current simulation and returning to the main menu.");
                break;
            }
        }
    }
}
