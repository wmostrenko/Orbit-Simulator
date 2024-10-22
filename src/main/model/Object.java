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
    private double xAcceleration;// in AU/yr^2
    private double yAcceleration; // in AU/yr^2

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
    public void updateObject(double timeStep, double deltaXAcceleration, double deltaYAcceleration,
            double referenceFrameXPosition, double referenceFrameYPosition, double referenceFrameXVelocity,
            double referenceFrameYVelocity, double referenceFrameXAcceleration, double referenceFrameYAcceleration) {
        updateAcceleration(deltaXAcceleration, deltaYAcceleration, referenceFrameXAcceleration,
                referenceFrameYAcceleration);
        updateVelocity(timeStep, referenceFrameXVelocity, referenceFrameYVelocity);
        updatePosition(timeStep, referenceFrameXPosition, referenceFrameYPosition);
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's position.
     */
    public void updatePosition(double timeStep, double referenceFrameXPosition, double referenceFrameYPosition) {
        this.xPosition += this.xVelocity * timeStep - referenceFrameXPosition;
        this.yPosition += this.yVelocity * timeStep - referenceFrameYPosition;
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's velocity.
     */
    public void updateVelocity(double timeStep, double referenceFrameXVelocity, double referenceFrameYVelocity) {
        this.xVelocity += this.xAcceleration * timeStep - referenceFrameXVelocity;
        this.yVelocity += this.yAcceleration * timeStep - referenceFrameYVelocity;
    }

    /*
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's acceleration given its change in acceleration.
     */
    public void updateAcceleration(double deltaXAcceleration, double deltaYAcceleration,
            double referenceFrameXAcceleration, double referenceFrameYAcceleration) {
        this.xAcceleration += deltaXAcceleration - referenceFrameXAcceleration;
        this.yAcceleration += deltaYAcceleration - referenceFrameYAcceleration;
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
