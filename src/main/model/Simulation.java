package model;

import persistence.Writable;
import java.util.ArrayList;
import logs.*;

import org.json.JSONObject;
import org.json.JSONArray;

/*
 * Represents the "mathematical backbone" of this application. This is where
 * Object elements and Grid tensors (to be later implemented) are updated,
 * and where reference frames are managed.
 */
public class Simulation implements Writable {
    private Object stationaryReferenceFrame;
    private Object currentReferenceFrame;
    private ArrayList<Object> objects;
    private static double ORIGINX;
    private static double ORIGINY;
    private double timeStep; // in yr
    private String name;

    /**
     * EFFECTS: Initializes stationaryReferenceFrame to a new Object(),
     * initializes currentReferenceFrame to point to stationaryReferenceFrame,
     * initializes objects as a new ArrayList(), adds stationaryReferenceFrame
     * to objects.
     * 
     * @param name     name of simulation, mostly used for file naming
     * @param timeStep time-step in which the simulation updates, used as time-step
     *                 in Euler's calculations
     * 
     */
    public Simulation(String name, double timeStep) {
        stationaryReferenceFrame = new Object(0.0, ORIGINX, ORIGINY, 0.0, 0.0, 0.0, 0.0);
        currentReferenceFrame = stationaryReferenceFrame;

        objects = new ArrayList<Object>();
        objects.add(stationaryReferenceFrame);

        this.timeStep = timeStep;
        this.name = name;
    }

    /**
     * MODIFIES: this.
     * EFFECTS: Adds object it to obejects.
     * 
     * @param object Object to add to simulation's objects
     */
    public void addObject(Object object) {
        if (object.getMass() != 0) {
            objects.add(object);
            EventLog.getInstance().logEvent(new Event("Added new object to simulation."));
        }
    }

    /**
     * REQUIRES: index < objects.size()
     * MODIFIES: this.
     * EFFECTS: Makes the indexed Object in objects null.
     * 
     * @param index index of Object in objects to remove
     */
    public void removeObject(int index) {
        objects.remove(index);
        EventLog.getInstance().logEvent(new Event("Removed object from simulation."));
    }

    /**
     * MODIFIES: this.
     * EFFECTS: Updates the properties of each Obejct in objects with respect to the
     * gravitational force between all the objects.
     */
    public void updateObjects() {
        for (Object currentObject : objects) {
            if (!currentObject.equals(currentReferenceFrame)) {
                currentObject.updateObject(timeStep, netDeltaAcceleration(currentObject, 0),
                                           netDeltaAcceleration(currentObject, 1), currentReferenceFrame.getXPosition(),
                                           currentReferenceFrame.getYPosition(), currentReferenceFrame.getXVelocity(),
                                           currentReferenceFrame.getYVelocity(), currentReferenceFrame.getXAcceleration(),
                                           currentReferenceFrame.getYAcceleration());
            }
        }
        currentReferenceFrame.updateObject(timeStep, netDeltaAcceleration(currentReferenceFrame, 0),
                                           netDeltaAcceleration(currentReferenceFrame, 1), currentReferenceFrame.getXPosition(),
                                           currentReferenceFrame.getYPosition(), currentReferenceFrame.getXVelocity(),
                                           currentReferenceFrame.getYVelocity(), currentReferenceFrame.getXAcceleration(),
                                           currentReferenceFrame.getYAcceleration());
    }

    /**
     * REQUIRES: object is in objects, mode is 0 or 1.
     * MODIFIES: object.
     * EFFECTS: Applies the acceleration changes to an object due to gravity from
     * all other objects.
     * 
     * @param referenceObject Object at which change in acceleration due to gravity
     *                        is calculated with
     *                        respect to
     * @param mode            mode to tell program which component to calculate
     *                        delta acceleration for, 0 is x
     *                        component, 1 is y component
     */
    public double netDeltaAcceleration(Object referenceObject, int mode) {
        // Local variable declaration
        double netDeltaAcceleration = 0;
        double deltaXPosition;
        double deltaYPosition;
        double deltaPosition;

        // Iterates through each object in Objects and upades object's net acceleration
        // by each Object in objects
        for (Object currentObject : objects) {
            deltaXPosition = deltaPosition(referenceObject.getXPosition(), currentObject.getXPosition());
            deltaYPosition = deltaPosition(referenceObject.getYPosition(), currentObject.getYPosition());
            deltaPosition = Math.hypot(deltaXPosition, deltaYPosition);
            if ((deltaPosition != 0) && (referenceObject.getMass() != 0)) {
                if (mode == 0) {
                    netDeltaAcceleration += deltaAcclerationFrom(currentObject.getMass(), deltaPosition,
                            deltaXPosition);
                } else if (mode == 1) {
                    netDeltaAcceleration += deltaAcclerationFrom(currentObject.getMass(), deltaPosition,
                            deltaYPosition);
                }
            }
        }

        return netDeltaAcceleration;
    }

    /**
     * REQUIRES: mass >= 0, deltaPosition > 0.
     * EFFECTS: Returns the change in acceleration of an object from another object.
     * 
     * @param mass              mass of an Object used to calculate gravitational
     *                          acceleration from
     * @param deltaPosition     distance between the two Objects used in
     *                          gravitational acceleration
     *                          calculations
     * @param deltaAxisPosition displacement on specified axis between the two
     *                          Objects used in
     *                          gravitational acceleration calculations
     */
    public double deltaAcclerationFrom(double mass, double deltaPosition, double deltaAxisPosition) {
        return 12.6 * mass * deltaAxisPosition / Math.pow(deltaPosition, 3);
    }

    /**
     * EFFECTS: Returns distance (magnitude and direction) between two points on an
     * axis.
     * 
     * @param otherPosition     single component position of Object being compared
     *                          against
     * @param referencePosition single component position of Object being referenced
     */
    public double deltaPosition(double otherPosition, double referencePosition) {
        return referencePosition - otherPosition;
    }

    /**
     * MODIFIES: this.
     * EFFECTS: Places object at the origin and changes positions, velocities, and
     * accelerations of all Objects in objects respectivly. Also updates the
     * simulation
     * to reflect these changes.
     * 
     * @param object Object to set at current reference frame
     */
    public void setCurrentReferenceFrame(Object object) {
        currentReferenceFrame = object;
        updateObjects();
        EventLog.getInstance().logEvent(new Event("Changed reference frame."));
    }

    /**
     * REQUIRES: index >= 0.
     * EFFECTS: Returns the object from objects at the given index.
     * 
     * @param index index of Object in objects to return
     */
    public Object getObjectAt(int index) {
        return this.objects.get(index);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonSim = new JSONObject();
        jsonSim.put("timeStep", timeStep);
        jsonSim.put("name", name);
        jsonSim.put("objects", objectsToJson());
        return jsonSim;
    }

    /*
     * EFFECTS: returns objects as a JSON array.
     */
    private JSONArray objectsToJson() {
        JSONArray jsonSimObjects = new JSONArray();
        for (Object currentObject : objects) {
            jsonSimObjects.put(currentObject.toJson());
        }
        return jsonSimObjects;
    }

    /**
     * EFFECTS: changes simulation name.
     * 
     * @param name new name for simulation
     */
    public void changeName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Changed simulation name."));
    }

    /**
     * EFFECTS: changes timeStep.
     * 
     * @param timeStep new time step for simulation
     */
    public void changeTimeStep(double timeStep) {
        this.timeStep = timeStep;
        EventLog.getInstance().logEvent(new Event("Changed simulation time step."));
    }

    public String getName() {
        return name;
    }

    public int getNumberOfObjects() {
        return objects.size();
    }

    public Object getCurrentReferenceFrame() {
        return currentReferenceFrame;
    }

    public Object getStationaryReferenceFrame() {
        return stationaryReferenceFrame;
    }

    public Double getTimeStep() {
        return timeStep;
    }
}
