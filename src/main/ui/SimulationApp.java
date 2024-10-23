package ui;

import java.util.Scanner;

import model.Simulation;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SimulationApp {
    private Simulation simulation; // dummy Simulation to add Simulation references to simulations
    private Scanner scanner;
    private double initialTimeStep; // user's initial timestep for new Simulations
    // private int input; // user's selected index for choosing options from a list.
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SimulationApp() {
        scanner = new Scanner(System.in);
        runSimulationApp();
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Opens the main menu and asks user which interface they would like to
     * open.
     */
    public void runSimulationApp() {
        // Local variable declaration
        int input;

        // Main menu loop
        while (true) {
            System.out.println(
                    "Would you like to make a new simulation (type the number, 1), enter a previous one (type the number, 2), or exit the application (type the number, 3)?");

            // User selects new or existing simulation
            while (true) {
                input = scanner.nextInt();
                scanner.nextLine();
                if ((input < 1) || (input > 3)) {
                    System.out.println("That isn't a valid option! Try again.");
                } else {
                    break;
                }
            }

            // Opens new interface depending on user's previous choice
            if (input == 1) {
                addSimulation();
                runSimulation(simulation);
            } else if (input == 2) {
                chooseExistingSimulation();
            } else {
                break;
            }
        }
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Lets the user create a new simulation with a given timestep.
     */
    public void addSimulation() {
        // Local variable declaration
        String name;

        // Resets simulation to null state
        simulation = null;

        // Asks user for name of simulation, sets name
        System.out.println("What should be the name of your simulation?");
        name = scanner.nextLine();
        System.out.println("The name is " + name);

        // Asks user for timeStep of simulation, sets timeStep
        System.out.println("What should be the initial timestep of the simulation (in yr)?");
        while (true) {
            initialTimeStep = scanner.nextDouble();
            if (initialTimeStep < 0) {
                System.out.println("Your timestep can't be negative! Try again.");
            } else {
                break;
            }
        }

        // Creates simulation
        simulation = new Simulation((String) name, initialTimeStep);
        System.out.println(name + " has been created with an initial timestep of " + initialTimeStep);
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Asks the user which Simulation they'd like to open, then opens that
     * Simulation. Catches IOException from loadSimulation() if name doesn't exist.
     */
    public void chooseExistingSimulation() {
        // Local variable declaration
        String name;

        // Asks user what simulation to enter
        System.out.println("Which simulation would you like to enter (type the name of the simulation)?");
        name = scanner.nextLine();

        // Loads and enteres simulation
        try {
            loadSimulation(name);
            runSimulation(simulation);
        } catch (IOException e) {
            System.out.println("Simulation with entered name does not exist.");
        }
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Runs the Simulation menu.
     */
    public void runSimulation(Simulation simulation) {
        // Local variable declaration
        ObjectTool objectTool = new ObjectTool(simulation);
        Boolean simulationRunning = true;
        int input;

        // Main simulation menu loop
        while (simulationRunning) {
            System.out.println("What would you like to do in your simulation?");
            System.out.println("1. Add an object.");
            System.out.println("2. Get an object's properties.");
            System.out.println("3. Update the simulation.");
            System.out.println("4. Change Reference Frame.");
            System.out.println("5. Save the simulation.");
            System.out.println("6. Close the simulation.");

            // Gets input from user
            while (true) {
                input = scanner.nextInt();
                scanner.nextLine();
                if ((input < 1) || (input > 6)) {
                    System.out.println("That is an invalid option! Please try again.");
                } else {
                    break;
                }
            }

            // Chooses option based on user's previous input
            switch (input) {
                case 1:
                    objectTool.addObject();
                    break;
                case 2:
                    objectTool.getObjectProperties();
                    break;
                case 3:
                    simulation.standardUpdateObjects();
                    break;
                case 4:
                    changeReferenceFrames();
                    break;
                case 5:
                    saveSimulation();
                    break;
                case 6:
                    simulationRunning = false;
                    break;
            }
        }
    }

    /*
     * MODIFIES: simulation.
     * EFFECTS: Changes the current reference frame in the simulation.
     */
    public void changeReferenceFrames() {
        // Local variable declaration
        int input;
        
        // Prints a list of each Object in objects
        System.out.println("The reference frame of which object would you like to enter?");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println(i + ". Object " + i);
        }

        // User entrers object # to observe
        while (true) {
            input = scanner.nextInt();
            scanner.nextLine();
            if ((input >= simulation.getNumberOfObjects()) || (input < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                break;
            }
        }
        simulation.setCurrentReferenceFrame(simulation.getObjectAt(input));
    }

    /*
     * EFFECTS: saves current simulation to a file.
     */
    private void saveSimulation() {
        try {
            jsonWriter = new JsonWriter("./data/" + simulation.getName() + ".json");
            jsonWriter.open();
            jsonWriter.write(simulation);
            jsonWriter.close();
            System.out.println("Simulation saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file \"" + simulation.getName() + "\".");
        }
    }

    /*
     * MODIFIES: this
     * REQUIRES: name.json is name of file for simulation
     * EFFECTS: loads simulation from simulation name.
     */
    private void loadSimulation(String name) throws IOException{
        jsonReader = new JsonReader("./data/" + name + ".json");
        simulation = jsonReader.read();
    }
}
