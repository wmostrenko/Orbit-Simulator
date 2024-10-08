package model;

import java.util.ArrayList;

/*
 * Represents the "mathematical backbone" of this application. This is where
 * Object elements and Grid tensors (to be later implemented) are updated,
 * and where reference frames are managed.
 */
public class Simulation {
    private Object stationaryReferenceFrame;
    private Object currentReferenceFrame;
    private ArrayList<Object> objects;
    private static double ORIGINX;
    private static double ORIGINY;
    private double timeStep; // in yr

    /*
     * EFFECTS: initializes stationaryReferenceFrame to a new Object(),
     * initializes currentReferenceFrame to point to stationaryReferenceFrame,
     * initializes objects as a new ArrayList(), adds stationaryReferenceFrame
     * to objects.
    */
    public Simulation(double timeStep) {
        stationaryReferenceFrame = new Object(0.0, ORIGINX, ORIGINY, 0.0, 0.0);
        currentReferenceFrame = stationaryReferenceFrame;
        objects = new ArrayList<Object>();
        objects.add(stationaryReferenceFrame);
        this.timeStep = timeStep;
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Adds object it to obejects.
     */
    public void addObject(Object object) {
        objects.add(object);
    }

    /* 
     * REQUIRES: index < objects.size()
     * MODIFIES: this.
     * EFFECTS: Makes the indexed Object in objects null.
     */
    public void removeObject(int index) {
        objects.remove(index);
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Updates the properties of each Obejct in objects with respect to the
     * gravitational force between all the objects.
     */
    public void standardUpdateObjects() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).updateObject(this.timeStep, netDeltaXAcceleration(objects.get(i), objects), netDeltaYAcceleration(objects.get(i), objects));
        }
    }

    /*
     * REQUIRES: object is in objects.
     * MODIFIES: object.
     * EFFECTS: Applies the acceleration changes to an object due to gravity from
     * all other objects.
     */
    public double netDeltaXAcceleration(Object object, ArrayList<Object> objects) {
        double netDeltaXAcceleration = 0;
        for (int i = 0; i < objects.size(); i++) {
            Object currentObject = objects.get(i);
            double deltaXPosition = deltaPosition(object.getXPosition(), currentObject.getXPosition());
            double deltaYPosition = deltaPosition(object.getYPosition(), currentObject.getYPosition());
            double deltaPosition = Math.hypot(deltaXPosition, deltaYPosition);
            if (deltaXPosition != 0) {
                netDeltaXAcceleration += deltaAcclerationFrom(currentObject.getMass(), deltaPosition, deltaXPosition);
            } else {
                continue;
            }
        }
        return netDeltaXAcceleration;
    }

    /*
     * REQUIRES: object is in objects.
     * MODIFIES: object.
     * EFFECTS: Applies the acceleration changes to an object due to gravity from
     * all other objects.
     */
    public double netDeltaYAcceleration(Object object, ArrayList<Object> objects) {
        double netDeltaYAcceleration = 0;
        for (int i = 0; i < objects.size(); i++) {
            Object currentObject = objects.get(i);
            double deltaXPosition = deltaPosition(object.getXPosition(), currentObject.getXPosition());
            double deltaYPosition = deltaPosition(object.getYPosition(), currentObject.getYPosition());
            double deltaPosition = Math.hypot(deltaXPosition, deltaYPosition);
            if (deltaXPosition != 0) {
                netDeltaYAcceleration += deltaAcclerationFrom(currentObject.getMass(), deltaPosition, deltaYPosition);
            } else {
                continue;
            }
        }
        return netDeltaYAcceleration;
    }
    /*
     * REQUIRES: mass >= 0, deltaPosition > 0.
     * EFFECTS: Returns the change in acceleration of an object from another object.
     */
    public double deltaAcclerationFrom(double mass, double deltaPosition, double deltaAxisPosition) {
        return 12.6 * mass * deltaAxisPosition / Math.pow(deltaPosition, 3);
    }

    /*
     * EFFECTS: Returns distance (magnitude and direction) between two points on an axis.
     */
    public double deltaPosition(double position1, double position2) {
        return position2 - position1;
    }

    public int getNumberOfObjects() {
        return objects.size();
    }

    public ArrayList<Object> getObjects() {
        return this.objects;
    }

}
