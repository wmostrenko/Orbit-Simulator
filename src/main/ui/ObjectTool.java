package ui;

import model.Simulation;

import java.util.Scanner;

import model.Object;

// Represents a tool that users can use to modify Objects in Simulations
public class ObjectTool {
    Simulation simulation;
    Object object;
    Scanner in = new Scanner(System.in);

    /*
     * EFFECTS: initializes this.simulation to simulation, initializes object to null.
     */
    public ObjectTool(Simulation simulation) {
        this.simulation = simulation;
        object = null;
    }

    /*
     * MODIFIES: this, simulation.
     * EFFECTS: A UI to add Objects to a Simulation
     */
    public void addObject() {
        double mass; // in SM
        double xPosition; // in AU
        double yPosition; // in AU
        double xVelocity; // in AU/yr
        double yVelocity; // in AU/yr

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
        int index; // index of object to be removed
        
        System.out.println("Which object below would you like to remove (#)? ");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println(i + ". Object " + i);
        }

        // User entrers object # to remove
        while (true) {
            index = in.nextInt();
            if ((index >= simulation.getNumberOfObjects()) || (index < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                break;
            }
        }
        simulation.removeObject(index);
    }
}
