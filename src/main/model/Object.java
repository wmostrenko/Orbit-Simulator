package model;

    /*
    MODIFIES: 
    REQUIRES: 
    EFFECTS:  
    */

// Represents a physical object with position (in 2D), velocity (in 2D), acceleration (in 2D), and a mass.
public class Object {
    private int mass;
    private int xPosition;
    private int yPosition;
    private int xVelocity;
    private int yVelocity;
    private int yAcceleration;
    private int xAcceleration;

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
        return this.mass;
    }
    
    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public int getYVelocity() {
        return this.yVelocity;
    }

    public int getXAcceleration() {
        return this.xAcceleration;
    }

    public int getYAcceleration() {
        return this.yAcceleration;
    }
}
