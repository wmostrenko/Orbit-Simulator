package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectTest {
    Object testObject1;
    Object testObject2;
    
    @BeforeEach
    void runBefore() {
        testObject1 = new Object(1, 2, 3, 4, 5);
        testObject2 = new Object(0, 10, 15, -5, 2);
    }

    @Test
    void constructorTest() {
        assertEquals(1, testObject1.getMass());
        assertEquals(2, testObject1.getXPosition());
        assertEquals(3, testObject1.getYPosition());
        assertEquals(4, testObject1.getXVelocity());
        assertEquals(5, testObject1.getYVelocity());
        assertEquals(0, testObject1.getXAcceleration());
        assertEquals(0, testObject1.getYAcceleration());
    }

    @Test
    void updatePositionTest() {
        testObject1.updatePosition(1, 0, 0);
        assertEquals(6, testObject1.getXPosition());
    }

    @Test
    void updateAcceleration() {
        testObject1.updateAcceleration(2, 0, 0, 0);
        assertEquals(2, testObject1.getXAcceleration());
    }

    @Test
    void updateVelocityTest() {
        testObject1.updateAcceleration(2, 0, 0, 0);
        testObject1.updateVelocity(1, 0, 0);
        assertEquals(6, testObject1.getXVelocity());
    }

    @Test
    void updateObjectTest() {
        testObject1.updateObject(1, 2, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(8, testObject1.getXPosition());
        assertEquals(6, testObject1.getXVelocity());
        assertEquals(2, testObject1.getXAcceleration());
    }

    @Test
    void updateObjectReferenceFrameChangingTest() {
        testObject2.updateObject(1, 0, 0, 1, 2, 3, 4, 5, 6);
        assertEquals(-4, testObject2.getXPosition());
        assertEquals(5, testObject2.getYPosition());
        assertEquals(-13, testObject2.getXVelocity());
        assertEquals(-8, testObject2.getYVelocity());
        assertEquals(-5, testObject2.getXAcceleration());
        assertEquals(-6, testObject2.getYAcceleration());
    }

}
