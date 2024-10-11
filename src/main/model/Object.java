package model;

/*
 * Represents a physical object with position (in 2D), velocity (in 2D), acceleration (in 2D),
 * and a mass.
 */
public class Object {
    private double mass; // in SM
    private double xPosition; // in AU
    private double yPosition; // in AU
    private double xVelocity; // in AU/yr
    private double yVelocity; // in AU/yr
    private double yAcceleration; // in AU/yr^2
    private double xAcceleration;// in AU/yr^2

    /*
     * REQUIRES: mass >= 0.
     * EFFECTS: Initializes this.mass to mass, initializes this.xPosition to
     * xPosition,
     * initializes this.xVelocity to xVelocity, initializes this.yVelocity to
     * yVelocity,
     * initializes this.xAcceleration to 0, initializes this.yAcceleration to 0.
     */
    public Object(double mass, double xPosition, double yPosition, double xVelocity, double yVelocity) {
        this.mass = mass;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xAcceleration = 0.0;
        this.yAcceleration = 0.0;
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's position, velocity, and acceleration.
     */
    public void updateObject(double timeStep, double deltaXAcceleration, double deltaYAcceleration) {
        updateAcceleration(deltaXAcceleration, deltaYAcceleration);
        updateVelocity(timeStep);
        updatePosition(timeStep);
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's position.
     */
    public void updatePosition(double timeStep) {
        this.xPosition += this.xVelocity * timeStep;
        this.yPosition += this.yVelocity * timeStep;
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's velocity.
     */
    public void updateVelocity(double timeStep) {
        this.xVelocity += this.xAcceleration * timeStep;
        this.yVelocity += this.yAcceleration * timeStep;
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's acceleration given its change in acceleration.
     */
    public void updateAcceleration(double deltaXAcceleration, double deltaYAcceleration) {
        this.xAcceleration += deltaXAcceleration;
        this.yAcceleration += deltaYAcceleration;
    }

    public double getMass() {
        return this.mass;
    }

    public double getXPosition() {
        return this.xPosition;
    }

    public double getYPosition() {
        return this.yPosition;
    }

    public double getXVelocity() {
        return this.xVelocity;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    public double getXAcceleration() {
        return this.xAcceleration;
    }

    public double getYAcceleration() {
        return this.yAcceleration;
    }
}
