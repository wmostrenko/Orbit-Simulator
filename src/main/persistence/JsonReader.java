package persistence;

import model.Object;
import model.Simulation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/* 
 * Referenced from the JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

/* 
 * Represents a reader that reads workroom from JSON data stored in file.
 */
public class JsonReader {
    private String source;
    /*
     * EFFECTS: constructs reader to read from source file.
    */
    public JsonReader(String source) {
        // TODO
    }

    /*
     * EFFECTS: reads simulation from file and returns it
     * throws IOException if an error occurs reading data from file.
    */
    public Simulation read() throws IOException {
        // TODO
    }

    /*
     * EFFECTS: reads source file as string and returns it.
    */
    private String readFile(String source) throws IOException {
        // TODO
    }

    /*
     * EFFECTS: parses simulation from JSON obejct and returns it.
    */
    private Simulation parseSimulation(JSONObject jsonObject) {
        // TODO
    }

    /*
     * MODIFIES: simulation
     * EFFECTS: parses Objects from jsonObject and adds them to the simulation.
    */
    private void addObjects(Simulation simulation, JSONObject jsonObject) {
        // TODO
    }

    /*
     * MODIFIES: simulation
     * EFFECTS: parses Object from JSON object and adds it to the simulation with all
     * its properties.
    */
    private void addObject(Simulation simulation, JSONObject jsonObject) {
        // TODO
    }
}
