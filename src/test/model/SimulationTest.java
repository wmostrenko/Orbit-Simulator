package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    private Simulation testSimulation;
    private Object testObject1;
    private Object testObject2;
    private Object testObject3;
    
    @BeforeEach
    void runBefore() {
        testSimulation = new Simulation(1.0);

        testObject1 = new Object(1, 0, 0, 0, 0);
        testObject2 = new Object(100, 10, 0, 0, 0);
        testObject3 = new Object(100, -10, 0, 0, 0);
    }

    @Test
    void constructorTest() {
        assertEquals(1, testSimulation.getNumberOfObjects());
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


}
