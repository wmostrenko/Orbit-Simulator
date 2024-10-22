package persistence;
import model.Object;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* 
 * Referenced from the JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonTest {
    protected void checkObject(Object object, double mass, double xPosition, double yPosition, double xVelocity, double yVelocity,
            double xAcceleration, double yAcceleration) {
                assertEquals(mass, object.getMass());
                assertEquals(xPosition, object.getXPosition());
                assertEquals(yPosition, object.getYPosition());
                assertEquals(xVelocity, object.getXVelocity());
                assertEquals(yVelocity, object.getYVelocity());
                assertEquals(xAcceleration, object.getXAcceleration());
                assertEquals(yAcceleration, object.getYAcceleration());
    }
}
