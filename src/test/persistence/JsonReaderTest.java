package persistence;

import model.Simulation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/* 
 * Referenced from the JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNoFile() {
        JsonReader testReader = new JsonReader("./testData/notARealFile.json");
        try {
            @SuppressWarnings("unused")
            Simulation testSimulation = testReader.read();
            fail("IOException expected!");
        } catch (IOException e) {
            // this should run
        }
    }

    @Test
    void testReaderEmptySimulation() {
        JsonReader testReader = new JsonReader("./testData/testReaderEmptySimulation.json");
        try {
            Simulation testSimulation = testReader.read();
            assertEquals("testReaderEmptySimulation", testSimulation.getName());
            assertEquals(1, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }

    @Test
    void testReaderOneObjectSimulation() {
        JsonReader testReader = new JsonReader("./testData/testReaderOneObjectSimulation.json");
        try {
            Simulation testSimulation = testReader.read();
            assertEquals("testReaderOneObjectSimulation", testSimulation.getName());
            assertEquals(2, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(1), 1, 2, 3, 4, 5, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }

    @Test
    void testReaderMultipleObjectsSimulation() {
        JsonReader testReader = new JsonReader("./testData/testReaderMultipleObjectsSimulation.json");
        try {
            Simulation testSimulation = testReader.read();
            assertEquals("testReaderMultipleObjectsSimulation", testSimulation.getName());
            assertEquals(3, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(1), 1, 2, 3, 4, 5, 0, 0);
            checkObject(testSimulation.getObjectAt(2), 100, 10, 0, 0, 2, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }
}
