package persistence;

import model.Object;
import model.Simulation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* 
 * Referenced from the JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReaderTest extends JsonTest {
    JsonReader testReader;
    Simulation testSimulation;

    @Test
    void testReaderNoFile() {
        testReader = new JsonReader("./data/notARealFile.json");
        try {
            testSimulation = testReader.read();
            fail("IOException expected!");
        } catch (IOException e) {
            // this should run
        }
    }

    @Test
    void testReaderEmptySimulation() {
        testReader = new JsonReader("./data/testReaderEmptySimulation.json");
        try {
            testSimulation = testReader.read();
            assertEquals(2, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }

    @Test
    void testReaderOneObjectSimulation() {
        testReader = new JsonReader("./data/testReaderOneObjectSimulation.json");
        try {
            testSimulation = testReader.read();
            assertEquals(2, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(1), 1, 2, 3, 4, 5, 6, 7);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }

    @Test
    void testReaderMultipleObjectsSimulation() {
        testReader = new JsonReader("./data/testReaderMultipleObjectsSimulation.json");
        try {
            testSimulation = testReader.read();
            assertEquals(2, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(1), 1, 2, 3, 4, 5, 6, 7);
            checkObject(testSimulation.getObjectAt(2), 100, 10, 0, 0, 2, 0, 1);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }


}
