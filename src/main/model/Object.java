package model;

import org.json.JSONObject;

import persistence.Writable;

/*
 * Represents a physical object with position (in 2D), velocity (in 2D), acceleration (in 2D),
 * and a mass.
 */
public class Object implements Writable{
    private double mass; // in SM
    private double xPosition; // in AU
    private double yPosition; // in AU
    private double xVelocity; // in AU/yr
    private double yVelocity; // in AU/yr
    private double xAcceleration;// in AU/yr^2
    private double yAcceleration; // in AU/yr^2

    /**
     * REQUIRES: mass >= 0.
     * EFFECTS: Initializes this.mass to mass, initializes this.xPosition to
     * xPosition, initializes this.xVelocity to xVelocity, initializes
     * this.yVelocity to yVelocity, initializes this.xAcceleration to 0, 
     * initializes this.yAcceleration to 0.
     * 
     * @param mass mass of the Object
     * @param xPosition x position of the Object with respect to the origin
     * @param yPosition y position of the Object with respect to the origin
     * @param xVelocity x velocity of the Object
     * @param yVelocity y velocity of the Object
     * @param xAcceleration x acceleration of the Object
     * @param yAcceleration y acceleration of the Object
     */
    public Object(double mass, double xPosition, double yPosition, double xVelocity, double yVelocity, double xAcceleration, double yAcceleration) {
        this.mass = mass;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xAcceleration = yAcceleration;
        this.yAcceleration = xAcceleration;
    }

    /**
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's position, velocity, and acceleration.
     * 
     * @param timeStep time-step in which the simulation updates, used as time-step in Euler's calculations
     * @param deltaXAcceleration change in x acceleration of the Object due to gravitational force
     * @param deltaYAcceleration change in y acceleration of the Object due to gravitational force
     * @param referenceFrameXPosition x position of the current reference frame
     * @param referenceFrameYPosition y position of the current reference frame
     * @param referenceFrameXVelocity x velocity of the current reference frame
     * @param referenceFrameYVelocity y velocity of the current reference frame
     * @param referenceFrameXAcceleration x acceleration of the current reference frame
     * @param referenceFrameYAcceleration y acceleration of the current reference frame
     */
    public void updateObject(double timeStep, double deltaXAcceleration, double deltaYAcceleration,
            double referenceFrameXPosition, double referenceFrameYPosition, double referenceFrameXVelocity,
            double referenceFrameYVelocity, double referenceFrameXAcceleration, double referenceFrameYAcceleration) {
        updateAcceleration(deltaXAcceleration, deltaYAcceleration, referenceFrameXAcceleration,
                referenceFrameYAcceleration);
        updateVelocity(timeStep, referenceFrameXVelocity, referenceFrameYVelocity);
        updatePosition(timeStep, referenceFrameXPosition, referenceFrameYPosition);
    }

    /**
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's position.
     * 
     * @param timeStep time-step in which the simulation updates, used as time-step in Euler's calculations
     * @param referenceFrameXPosition x position of the current reference frame
     * @param referenceFrameYPosition y position of the current reference frame
     */
    public void updatePosition(double timeStep, double referenceFrameXPosition, double referenceFrameYPosition) {
        this.xPosition += this.xVelocity * timeStep - referenceFrameXPosition;
        this.yPosition += this.yVelocity * timeStep - referenceFrameYPosition;
    }

    /**
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's velocity.
     * 
     * @param timeStep time-step in which the simulation updates, used as time-step in Euler's calculations
     * @param referenceFrameXVelocity x velocity of the current reference frame
     * @param referenceFrameYVelocity y velocity of the current reference frame
     */
    public void updateVelocity(double timeStep, double referenceFrameXVelocity, double referenceFrameYVelocity) {
        this.xVelocity += this.xAcceleration * timeStep - referenceFrameXVelocity;
        this.yVelocity += this.yAcceleration * timeStep - referenceFrameYVelocity;
    }

    /**
     * REQUIRES: timeStep >= 0
     * MODIFIES: object
     * EFFECTS: Updates object's acceleration given its change in acceleration and acceleration of current
     * reference frame.
     * 
     * @param timeStep time-step in which the simulation updates, used as time-step in Euler's calculations
     * @param referenceFrameXAcceleration x acceleration of the current reference frame
     * @param referenceFrameYAcceleration y acceleration of the current reference frame
     */
    public void updateAcceleration(double deltaXAcceleration, double deltaYAcceleration,
            double referenceFrameXAcceleration, double referenceFrameYAcceleration) {
        this.xAcceleration += deltaXAcceleration - referenceFrameXAcceleration;
        this.yAcceleration += deltaYAcceleration - referenceFrameYAcceleration;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonSimObject = new JSONObject();
        jsonSimObject.put("mass", mass);
        jsonSimObject.put("xPosition", xPosition);
        jsonSimObject.put("yPosition", yPosition);
        jsonSimObject.put("xVelocity", xVelocity);
        jsonSimObject.put("yVelocity", yVelocity);
        jsonSimObject.put("xAcceleration", xAcceleration);
        jsonSimObject.put("yAcceleration", yAcceleration);
        return jsonSimObject;
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
