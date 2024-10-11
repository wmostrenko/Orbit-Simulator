package ui;

import model.Simulation;

import java.util.Scanner;

import model.Object;

// Represents a tool that users can use to modify Objects in Simulations
public class ObjectTool {
    private Simulation simulation;
    private Object object; // dummy variable for manipulating objects
    private Scanner in = new Scanner(System.in);
    private int userIntInput; // dummy variable for user's last integer input
    private double mass; // in SM
    private double xPosition; // in AU
    private double yPosition; // in AU
    private double xVelocity; // in AU/yr
    private double yVelocity; // in AU/yr

    /*
     * EFFECTS: Initializes this.simulation to simulation, initializes object to null.
     */
    public ObjectTool(Simulation simulation) {
        this.simulation = simulation;
        object = null;
    }

    /*
     * MODIFIES: this, simulation.
     * EFFECTS: A UI to add Objects to a Simulation.
     */
    public void addObject() {
        // User enters mass
        System.out.println("What is the mass (in solar masses)?");
        while (true) {
            mass = in.nextDouble();
            if (mass < 0.0) {
                System.out.println("The mass cannot be negative! Try again.");
            } else {
                break;
            }
        }
        System.out.println("The mass is set to " + mass + "SM");

        // User enters xPosition
        System.out.println("What should the initial horizontal position be (in astronomical units)?");
        xPosition = in.nextDouble();
        System.out.println("The horizontal position is set to " + xPosition + "AU");

        // User enters yPosition
        System.out.println("What should the initial vertical position be (in astronomical units)?");
        yPosition = in.nextDouble();
        System.out.println("The vertical position is set to " + yPosition + "AU");

        // User enters xVelocity
        System.out.println("What should the initial horizontal velocity be (in astronomical units per year)?");
        xVelocity = in.nextDouble();
        System.out.println("The initial horizontal velocity is set to " + xVelocity + "AU/yr");

        // User enters yVelocity
        System.out.println("What should the initial vertical velocity be (in astronomical units per year)?");
        yVelocity = in.nextDouble();
        System.out.println("The initial vertical velocity is set to " + yVelocity + "AU/yr");

        // Adds object to simulation
        this.object = new Object(mass, xPosition, yPosition, xVelocity, yVelocity);
        this.simulation.addObject(this.object);
        System.out.println("Your new object has been added!");
    }

    /*
     * MODIFIES: this, simulation.
     * EFFECTS: A UI to remove Objects from a Simulation
     */
    public void removeObject() {        
        System.out.println("Which object below would you like to remove (#)? ");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println(i + ". Object " + i);
        }

        // User entrers object # to remove
        while (true) {
            userIntInput = in.nextInt();
            if ((userIntInput >= simulation.getNumberOfObjects()) || (userIntInput < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                simulation.removeObject(userIntInput);
                break;
            }
        }
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Asks user which object they would like to view. Then prints object properties.
     */
    public void getObjectProperties() {      
        // Prints a list of each Object in objects
        System.out.println("The properties of which object below would you like to view?");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println(i + ". Object " + i);
        }

        // User entrers object # to observe
        while (true) {
            userIntInput = in.nextInt();
            if ((userIntInput >= simulation.getNumberOfObjects()) || (userIntInput < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                break;
            }
        }

        // Prints out properties of chosen object
        Object observedObject = simulation.getObjectAt(userIntInput);
        for (int i = 0; i < 7; i++) {
            switch(i) {
                case 0:
                    System.out.println("Mass: " + observedObject.getMass());
                    break;
                case 1:
                    System.out.println("xPosition: " + observedObject.getXPosition());
                    break;
                case 2:
                    System.out.println("yPosition: " + observedObject.getYPosition());
                    break;
                case 3:
                    System.out.println("xVelocity: " + observedObject.getXVelocity());
                    break;
                case 4:
                    System.out.println("yVelocity: " + observedObject.getYVelocity());
                    break;
                case 5:
                    System.out.println("xAcceleration: " + observedObject.getXAcceleration());
                    break;
                case 6:
                    System.out.println("yAcceleration: " + observedObject.getYAcceleration());
                    break;
            }
        }
    }
}
