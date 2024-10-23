package ui;

import java.util.Scanner;

import model.Simulation;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SimulationApp {
    // ArrayList<Simulation> simulations = new ArrayList<Simulation>();
    private Simulation simulation; // dummy Simulation to add Simulation references to simulations
    private Scanner in;
    private double initialTimeStep; // user's initial timestep for new Simulations
    private int userIntInput; // user's selected index for choosing options from a list.
    // private String userStringInput; // user's string inputs.
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SimulationApp() {
        in = new Scanner(System.in);
        runSimulationApp();
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Opens the main menu and asks user which interface they would like to
     * open.
     */
    public void runSimulationApp() {
        while (true) {
            System.out.println(
                    "Would you like to make a new simulation (type the number, 1), enter a previous one (type the number, 2), or exit the application (type the number, 3)?");

            // User selects new or existing simulation
            while (true) {
                userIntInput = in.nextInt();
                in.nextLine();
                if ((userIntInput < 1) || (userIntInput > 3)) {
                    System.out.println("That isn't a valid option! Try again.");
                } else {
                    break;
                }
            }

            // Opens new interface depending on user's previous choice
            if (userIntInput == 1) {
                addSimulation();
                runSimulation(simulation);
            } else if (userIntInput == 2) {
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
        simulation = null;
        System.out.println("What should be the name of your simulation?");
        String userStringInput = in.nextLine();
        System.out.println("The name is " + userStringInput);

        System.out.println("What should be the initial timestep of the simulation (in yr)?");
        while (true) {
            initialTimeStep = in.nextDouble();
            if (initialTimeStep < 0) {
                System.out.println("Your timestep can't be negative! Try again.");
            } else {
                break;
            }
        }

        simulation = new Simulation((String) userStringInput, initialTimeStep);
        System.out.println(userStringInput + " has been created with an initial timestep of " + initialTimeStep);
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Asks the user which Simulation they'd like to open, then opens that
     * Simulation.
     */
    public void chooseExistingSimulation() {
        System.out.println("Which simulation would you like to enter (type the name of the simulation)?");
        String userStringInput = in.nextLine();
        try {
            loadSimulation(userStringInput);
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
        ObjectTool objectTool = new ObjectTool(simulation);
        Boolean simulationRunning = true;
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
                userIntInput = in.nextInt();
                in.nextLine();
                if ((userIntInput < 1) || (userIntInput > 6)) {
                    System.out.println("That is an invalid option! Please try again.");
                } else {
                    break;
                }
            }

            // Chooses option based on user's previous input
            switch (userIntInput) {
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
        // Prints a list of each Object in objects
        System.out.println("The reference frame of which object would you like to enter?");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println(i + ". Object " + i);
        }

        // User entrers object # to observe
        while (true) {
            userIntInput = in.nextInt();
            in.nextLine();
            if ((userIntInput >= simulation.getNumberOfObjects()) || (userIntInput < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                break;
            }
        }
        simulation.setCurrentReferenceFrame(simulation.getObjectAt(userIntInput));
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
