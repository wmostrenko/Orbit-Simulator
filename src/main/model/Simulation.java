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
     * REQUIRES: objects.size() > 0
     * MODIFIES: this.
     * EFFECTS: Removes object from objects.
     */
    public void removeObject(Object object) {
        // TODO
    }

    /* 
     * MODIFIES: this.
     * EFFECTS: Updates the properties of each Obejct in objects with respect to the
     * gravitational force between all the objects.
     */
    public void standardUpdateObjects() {
        // TODO
    }

    public Object getCurrentReferenceFrame() {
        // TODO
    }

    public int getNumberOfObjects() {
        // TODO
    }

}
