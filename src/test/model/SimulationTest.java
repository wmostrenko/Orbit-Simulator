package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    private Simulation testSimulation;
    private Object testObject1;
    private Object testObject2;
    private Object testObject3;
    private Object testObject4;
    private Object testObject5;
    
    @BeforeEach
    void runBefore() {
        testSimulation = new Simulation("A", 1.0);

        testObject1 = new Object(1, 0, 0, 0, 0, 0, 0);
        testObject2 = new Object(100, 10, 0, 0, 0, 0, 0);
        testObject3 = new Object(100, -10, 0, 0, 0, 0, 0);
        testObject4 = new Object(0, 0, 0, 0, 0, 0, 0);
        testObject5 = new Object(5, 10, 0, 0, 0, 0, 0);
    }

    @Test
    void constructorTest() {
        assertEquals(1, testSimulation.getNumberOfObjects());
        assertEquals("A", testSimulation.getName());
    }

    @Test
    void testGetObejctAt() {
        testSimulation.addObject(testObject1);
        assertEquals(testObject1, testSimulation.getObjectAt(1));
    }

    @Test
    void addObjectTest() {
        testSimulation.addObject(testObject1);
        assertEquals(2, testSimulation.getNumberOfObjects());
    }

    @Test
    void removeObjectTest() {
        testSimulation.addObject(testObject1);
        testSimulation.removeObject(1);
        assertEquals(1, testSimulation.getNumberOfObjects());
    }

    // Tests updateObjects when deltaXPosition/deltaYPosition >= 0
    @Test
    void updateObjectsGEQTest() {
        testSimulation.addObject(testObject1);
        testSimulation.addObject(testObject2);
        testSimulation.updateObjects();
        assertEquals(12.6, testObject1.getXAcceleration(), 0.01 * 12.6);
        assertEquals(12.6, testObject1.getXVelocity(), 0.01 * 12.6);
        assertEquals(12.6, testObject1.getXPosition(), 0.01 * 12.6);

        assertEquals(1.86, testObject2.getXAcceleration(), 0.01 * 1.86);
        assertEquals(1.86, testObject2.getXVelocity(), 0.01 * 1.86);
        assertEquals(11.86, testObject2.getXPosition(), 0.01 * 11.86);
    }

    // Tests updateObjects when deltaXPosition/deltaYPosition <= 0
    @Test
    void updateObjectsLEQTest() {
        testSimulation.addObject(testObject1);
        testSimulation.addObject(testObject3);
        testSimulation.updateObjects();
        assertEquals(-12.6, testObject1.getXAcceleration(), 0.01 * 12.6);
        assertEquals(-12.6, testObject1.getXVelocity(), 0.01 * 12.6);
        assertEquals(-12.6, testObject1.getXPosition(), 0.01 * 12.6);

        assertEquals(-1.86, testObject3.getXAcceleration(), 0.01 * 1.86);
        assertEquals(-1.86, testObject3.getXVelocity(), 0.01 * 1.86);
        assertEquals(-11.86, testObject3.getXPosition(), 0.01 * 11.86);
    }

    // Tests updateObject between an object of non-zero mass, and a massless object
    @Test
    void updateObjectsOnMasslessTest() {
        testSimulation.addObject(testObject2);
        testSimulation.addObject(testObject4);
        testSimulation.updateObjects();
        assertEquals(0, testObject2.getXAcceleration());
        assertEquals(0, testObject2.getXVelocity());
        assertEquals(10, testObject2.getXPosition());

        assertEquals(0, testObject4.getXAcceleration());
        assertEquals(0, testObject4.getXVelocity());
        assertEquals(0, testObject4.getXPosition());
    }

    // Tests the changing of a reference frame
    @Test
    void changeReferenceFramesTest() {
        testSimulation.addObject(testObject5);
        testSimulation.setCurrentReferenceFrame(testObject5);
        assertEquals(testObject5, testSimulation.getCurrentReferenceFrame());
    }

    // Tests the reference frame updating effects
    @Test
    void changeReferenceFramesPositionTest() {
        testSimulation.addObject(testObject5);
        testSimulation.setCurrentReferenceFrame(testObject5);

        assertEquals(0, testObject5.getXPosition());
        assertEquals(-10, testSimulation.getStationaryReferenceFrame().getXPosition());
    }

    // Tests the changing of simulation name
    @Test
    void changeNameTest() {
        testSimulation.changeName("B");
        assertEquals("B", testSimulation.getName());
    }

    // Tests the changing of simulation time step
    @Test
    void changeTimeStepTest() {
        testSimulation.changeTimeStep(10);
        assertEquals(10, testSimulation.getTimeStep());
    }
}
