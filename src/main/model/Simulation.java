package model;

import java.util.ArrayList;

import model.Object;

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
        // TODO
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Removes object from objects.
     */
    public void removeObject(Object object) {
        // TODO
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /* 
     * MODIFIES: this.
     * EFFECTS: Points currentReferenceFrame to object
     */
    public void switchReferenceFrame(Object object) {
        // TODO
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Ensures currentReferenceFrame stays at origin with zero velocity/acceleration
     * and updates other Objects in objects so relative positions/velocities/accelerations
     * remain constant.
     */
    public void updateReferenceFrame() {
        // TODO
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Object getCurrentReferenceFrame() {
        // TODO
    }

    public int getNumberOfObjects() {
        // TODO
    }

}
