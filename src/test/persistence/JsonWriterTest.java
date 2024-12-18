package persistence;

import model.Object;
import model.Simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/* 
 * Referenced from the JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonWriterTest extends JsonTest{
    private Object testObject1;
    private Object testObject2;

    @BeforeEach
    void runBefore() {
        testObject1 = new Object(1, 0, 0, 0, 0, 0, 0);
        testObject2 = new Object(100, 10, 1, 2, 3, 0, 0);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter testWriter = new JsonWriter("./testData/my\0illegal:fileName.json");
            testWriter.open();
            fail("IOException expected!");
        } catch (IOException e) {
            // this should run
        }
    }

    @Test
    void testWriteEmptySimulation() {
        try {
            Simulation testSimulation = new Simulation("A", 1);

            JsonWriter testWriter = new JsonWriter("./testData/testWriteEmptySimulation.json");

            testWriter.open();
            testWriter.write(testSimulation);
            testWriter.close();

            JsonReader testReader = new JsonReader("./testData/testWriteEmptySimulation.json");
            testSimulation = testReader.read();

            assertEquals("A", testSimulation.getName());
            assertEquals(1, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            assertEquals(1, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }

    @Test
    void testWriteOneObjectSimulation() {
        try {
            Simulation testSimulation = new Simulation("A", 1);
            testSimulation.addObject(testObject1);

            JsonWriter testWriter = new JsonWriter("./testData/testWriteOneObjectSimulation.json");

            testWriter.open();
            testWriter.write(testSimulation);
            testWriter.close();

            JsonReader testReader = new JsonReader("./testData/testWriteOneObjectSimulation.json");
            testSimulation = testReader.read();

            assertEquals("A", testSimulation.getName());
            assertEquals(2, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(1), 1, 0, 0, 0, 0, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }

    @Test
    void testWriteMultipleObjectsSimulation() {
        try {
            Simulation testSimulation = new Simulation("A", 1);
            testSimulation.addObject(testObject1);
            testSimulation.addObject(testObject2);

            JsonWriter testWriter = new JsonWriter("./testData/testWriteMultipleObjectsSimulation.json");

            testWriter.open();
            testWriter.write(testSimulation);
            testWriter.close();

            JsonReader testReader = new JsonReader("./testData/testWriteMultipleObjectsSimulation.json");
            testSimulation = testReader.read();

            assertEquals("A", testSimulation.getName());
            assertEquals(3, testSimulation.getNumberOfObjects());
            checkObject(testSimulation.getObjectAt(0), 0, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(1), 1, 0, 0, 0, 0, 0, 0);
            checkObject(testSimulation.getObjectAt(2), 100, 10, 1, 2, 3, 0, 0);
        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }
}
