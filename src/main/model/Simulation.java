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
    private static int ORIGINX;
    private static int ORIGINY;
    private static int timeStep = 1;

    /*
     * EFFECTS: initializes stationaryReferenceFrame to a new Object(),
     * initializes currentReferenceFrame to point to stationaryReferenceFrame,
     * initializes objects as a new ArrayList(), adds stationaryReferenceFrame
     * to objects.
    */
    public Simulation() {
        stationaryReferenceFrame = new Object(0, ORIGINX, ORIGINY, 0, 0);
        currentReferenceFrame = stationaryReferenceFrame;
        objects = new ArrayList<Object>();
        objects.add(stationaryReferenceFrame);
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Adds object it to obejects.
     */
    public void addObject(Object object) {
        objects.add(object);
    }

    /* 
     * REQUIRES: objects.size() > 0
     * MODIFIES: this.
     * EFFECTS: Removes object from objects.
     */
    public void removeObject(Object object) {
        objects.remove(objects.indexOf(object));
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Updates the properties of each Obejct in objects with respect to the
     * gravitational force between all the objects.
     */
    public void standardUpdateObjects() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).updateObject(Simulation.timeStep, netDeltaXAcceleration(objects.get(i), objects), netDeltaYAcceleration(objects.get(i), objects));
        }
    }

    /*
     * REQUIRES: object is in objects.
     * MODIFIES: object.
     * EFFECTS: Applies the acceleration changes to an object due to gravity from
     * all other objects.
     */
    public int netDeltaXAcceleration(Object object, ArrayList<Object> objects) {
        int netDeltaXAcceleration = 0;
        for (int i = 0; i < (objects.size() - 1); i++) {
            Object currentObject = objects.get(i);
            if (object.equals(currentObject)) {
                continue;
            } else {
                int deltaXPosition = deltaPosition(object.getXPosition(), currentObject.getXPosition());
                int deltaYPosition = deltaPosition(object.getYPosition(), currentObject.getYPosition());
                int deltaPosition = (int)(Math.hypot(deltaXPosition, deltaYPosition));
                if (deltaXPosition > 0) {
                    netDeltaXAcceleration += deltaAcclerationFrom(currentObject.getMass(), deltaPosition, deltaXPosition);
                } else if (deltaXPosition < 0) {
                    netDeltaXAcceleration -= deltaAcclerationFrom(currentObject.getMass(), deltaPosition, deltaXPosition);
                } else {
                    continue;
                }
            }
        }
        return netDeltaXAcceleration;
    }

    public int netDeltaYAcceleration(Object object, ArrayList<Object> objects) {
        int netDeltaYAcceleration = 0;
        for (int i = 0; i < (objects.size() - 1); i++) {
            Object currentObject = objects.get(i);
            if (object.equals(currentObject)) {
                continue;
            } else {
                int deltaXPosition = deltaPosition(object.getXPosition(), currentObject.getXPosition());
                int deltaYPosition = deltaPosition(object.getYPosition(), currentObject.getYPosition());
                int deltaPosition = (int)(Math.hypot(deltaXPosition, deltaYPosition));
                if (deltaYPosition > 0) {
                    netDeltaYAcceleration += deltaAcclerationFrom(currentObject.getMass(), deltaPosition, deltaYPosition);
                } else if (deltaYPosition < 0) {
                    netDeltaYAcceleration -= deltaAcclerationFrom(currentObject.getMass(), deltaPosition, deltaYPosition);
                } else {
                    continue;
                }
            }
        }
        return netDeltaYAcceleration;
    }

    public int deltaAcclerationFrom(int mass, int deltaPosition, int deltaAxisPosition) {
        return (int)(6.67 * Math.pow(10, -11) * (double)(mass) * (double)(deltaAxisPosition) / Math.pow(deltaPosition, 3));
    }

    public int deltaPosition(int position1, int position2) {
        return position2 - position1;
    }

    public Object getCurrentReferenceFrame() {
        return currentReferenceFrame;
    }

    public int getNumberOfObjects() {
        return objects.size();
    }

}
