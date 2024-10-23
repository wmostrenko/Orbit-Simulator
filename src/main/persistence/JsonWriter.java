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
    private String source;

    /**
     * EFFECTS: constructs writer to write to destination file.
     * 
     * @param source directory of file to read
     */
    public JsonWriter(String source) {
        this.source = source;
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens writer. throws FileNotFoundException if destination file
     * cannot be opened for writing.
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(source));
    }

    /**
     * MODIFIES: this
     * EFFECTS: writes JSON representaiton of simulation to file.
     * 
     * @param simulation Simulation used to save state data to file
     */
    public void write(Simulation simulation) {
        JSONObject jsonSim = simulation.toJson();
        saveToFile(jsonSim.toString(TAB));
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes the writer.
     */
    public void close() {
        writer.close();
    }

    /**
     * MODIFIES: this
     * EFFECTS: writes string to file.
     * 
     * @param json long string of Simulation state data to convert to
     * .json file
     */
    private void saveToFile(String json) {
        writer.print(json);
    }
}
