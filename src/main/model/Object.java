package model;

    /*
    MODIFIES: 
    REQUIRES: 
    EFFECTS:  
    */

// Represents a physical object with position (in 2D), velocity (in 2D), acceleration (in 2D), and a mass.
public class Object {
    int mass;
    int xPosition;
    int yPosition;
    int xVelocity;
    int yVelocity;
    int yAcceleration;
    int xAcceleration;

    /*
     * REQUIRES: mass >= 0.
     * EFFECTS: initializes this.mass to mass, initializes this.xPosition to xPosition,
     * initializes this.xVelocity to xVelocity, initializes this.yVelocity to yVelocity,
     * initializes this.xAcceleration to 0, initializes this.yAcceleration to 0.
    */
    public Object(int mass, int xPosition, int yPosition, int xVelocity, int yVelocity) {
        this.mass = mass;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xAcceleration = 0;
        this.yAcceleration = 0;
    }

    public int getMass() {
        // TODO
    }
    
    public int getXPosition() {
        // TODO
    }

    public int getYPosition() {
        // TODO
    }

    public int getXVelocity() {
        // TODO
    }

    public int getYVelocity() {
        // TODO
    }

    public int getXAcceleration() {
        // TODO
    }

        public int getYAcceleration() {
        // TODO
    }
}
