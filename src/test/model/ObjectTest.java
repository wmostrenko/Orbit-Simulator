package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectTest {
    Object testObject;
    
    @BeforeEach
    void runBefore() {
        testObject = new Object(1, 2, 3, 4, 5);
    }

    @Test
    void constructorTest() {
        assertEquals(1, testObject.getMass());
        assertEquals(2, testObject.getXPosition());
        assertEquals(3, testObject.getYPosition());
        assertEquals(4, testObject.getXVelocity());
        assertEquals(5, testObject.getYVelocity());
        assertEquals(0, testObject.getXAcceleration());
        assertEquals(0, testObject.getYAcceleration());
    }

    @Test
    void updatePositionTest() {
        testObject.updatePosition(1);
        assertEquals(6, testObject.getXPosition());
    }

    @Test
    void updateAcceleration() {
        testObject.updateAcceleration(2, 0);
        assertEquals(2, testObject.getXAcceleration());
    }

    @Test
    void updateVelocityTest() {
        testObject.updateAcceleration(2, 0);
        testObject.updateVelocity(1);
        assertEquals(6, testObject.getXVelocity());
    }

    @Test
    void updateObjectTest() {
        testObject.updateObject(1, 2, 0);
        assertEquals(8, testObject.getXPosition());
        assertEquals(6, testObject.getXVelocity());
        assertEquals(2, testObject.getXAcceleration());
    }

}
