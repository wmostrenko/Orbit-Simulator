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
    
    @BeforeEach
    void runBefore() {
        testSimulation = new Simulation(1.0);

        testObject1 = new Object(1, 0, 0, 0, 0);
        testObject2 = new Object(100, 10, 0, 0, 0);
        testObject3 = new Object(100, -10, 0, 0, 0);
        testObject4 = new Object(0, 0, 0, 0, 0);
    }

    @Test
    void constructorTest() {
        assertEquals(1, testSimulation.getNumberOfObjects());
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

    // Tests standardUpdateObject when deltaXPosition/deltaYPosition >= 0
    @Test
    void testStandardUpdateObjectsGEQ() {
        testSimulation.addObject(testObject1);
        testSimulation.addObject(testObject2);
        testSimulation.standardUpdateObjects();
        assertEquals(12.6, testObject1.getXAcceleration(), 0.01 * 12.6);
        assertEquals(12.6, testObject1.getXVelocity(), 0.01 * 12.6);
        assertEquals(12.6, testObject1.getXPosition(), 0.01 * 12.6);

        assertEquals(1.86, testObject2.getXAcceleration(), 0.01 * 1.86);
        assertEquals(1.86, testObject2.getXVelocity(), 0.01 * 1.86);
        assertEquals(11.86, testObject2.getXPosition(), 0.01 * 11.86);
    }

    // Tests standardUpdateObject when deltaXPosition/deltaYPosition <= 0
    @Test
    void testStandardUpdateObjectsLEQ() {
        testSimulation.addObject(testObject1);
        testSimulation.addObject(testObject3);
        testSimulation.standardUpdateObjects();
        assertEquals(-12.6, testObject1.getXAcceleration(), 0.01 * 12.6);
        assertEquals(-12.6, testObject1.getXVelocity(), 0.01 * 12.6);
        assertEquals(-12.6, testObject1.getXPosition(), 0.01 * 12.6);

        assertEquals(-1.86, testObject3.getXAcceleration(), 0.01 * 1.86);
        assertEquals(-1.86, testObject3.getXVelocity(), 0.01 * 1.86);
        assertEquals(-11.86, testObject3.getXPosition(), 0.01 * 11.86);
    }

    // Tests standardUpdateObject between an object of non-zero mass, and a massless object
    @Test
    void testStandardUpdateObjectsOnMassless() {
        testSimulation.addObject(testObject2);
        testSimulation.addObject(testObject4);
        testSimulation.standardUpdateObjects();
        assertEquals(0, testObject2.getXAcceleration());
        assertEquals(0, testObject2.getXVelocity());
        assertEquals(10, testObject2.getXPosition());

        assertEquals(0, testObject4.getXAcceleration());
        assertEquals(0, testObject4.getXVelocity());
        assertEquals(0, testObject4.getXPosition());
    }


}
