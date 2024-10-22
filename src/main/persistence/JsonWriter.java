package persistence;
import model.Simulation;
import org.json.JSONObject;


import java.io.*;

/* 
 * Referenced from the JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

/*
 * Represents a writer that writes JSON representation of workroom to file .
 */
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    /*
     * EFFECTS: constructs writer to write to destination file.
     */
    public JsonWriter(String destination) {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens writer. throws FileNotFoundException if destination file
     * cannot be opened for writing.
     */
    public void open() throws FileNotFoundException {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representaiton of simulation to file.
     */
    public void write(Simulation simulation) {
        // STUB
    }

    /* 
     * MODIFIES: this
     * EFFECTS: writes string to file.
     */
    private void saveToFile(String json) {
        // STUB
    }
}
