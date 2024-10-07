package model;

import model.Object;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    Simulation testSimulation;
    Object testObject1;
    Object testObject2;
    
    @BeforeEach
    void runBefore() {
        testSimulation = new Simulation();

        testObject1 = new Object((int)(Math.pow(10, 11)), 1, 0, 0, 0);
        testObject2 = new Object(1, 0, 0, 1, 0);
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
        testSimulation.removeObject(testObject1);
        assertEquals(1, testSimulation.getNumberOfObjects());
    }
}
