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
        this.source = source;
    }

    /*
     * EFFECTS: reads simulation from file and returns it
     * throws IOException if an error occurs reading data from file.
    */
    public Simulation read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSimulation(jsonObject);
    }

    /*
     * EFFECTS: reads source file as string and returns it.
    */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: parses simulation from JSON obejct and returns it.
    */
    private Simulation parseSimulation(JSONObject jsonObject) {
        double timeStep = jsonObject.getDouble("timeStep");
        String name = jsonObject.getString("name");
        Simulation simulation = new Simulation(name, timeStep);

        addObjects(simulation, jsonObject);

        return simulation;
    }

    /*
     * MODIFIES: simulation
     * EFFECTS: parses Objects from jsonObject and adds them to the simulation.
    */
    private void addObjects(Simulation simulation, JSONObject jsonObject) {
        JSONArray jsonSimObjects = jsonObject.getJSONArray("objects");
        for (java.lang.Object jsonSimObject : jsonSimObjects) {
            JSONObject currentJsonSimObject = (JSONObject) jsonSimObject;
            addObject(simulation, currentJsonSimObject);
        }
    }

    /*
     * MODIFIES: simulation
     * EFFECTS: parses Object from JSON object and adds it to the simulation with all
     * its properties.
    */
    private void addObject(Simulation simulation, JSONObject jsonSimObject) {
        double mass = jsonSimObject.getDouble("mass");
        double xPosition = jsonSimObject.getDouble("xPosition");
        double yPosition = jsonSimObject.getDouble("yPosition");
        double xVelocity = jsonSimObject.getDouble("xVelocity");
        double yVelocity = jsonSimObject.getDouble("yVelocity");
        double xAcceleration = jsonSimObject.getDouble("xAcceleration");
        double yAcceleration = jsonSimObject.getDouble("yAcceleration");

        Object object = new Object(mass, xPosition, yPosition, xVelocity, yVelocity, xAcceleration, yAcceleration);

        simulation.addObject(object);
    }
}