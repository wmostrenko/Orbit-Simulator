package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Simulation;

/*
 * Represents an abstract window that consists of a list of buttons, where each button
 * has corresponds to a specific action.
 */
public abstract class ButtonWindow extends SubWindow {

    /**
     * EFFECTS: Constructs a window for whcih users.
     * 
     * @param title title of the window.
     * @param width width of the window.
     * @param height height of the window.
     * @param simulation Simulation for which the users will use the ButtonWindow to interact
     * with.
     */
    public ButtonWindow(String title, Integer width, Integer height, Simulation simulation) {
        super(title, width, height, simulation);
    }

    /**
     * EFFECTS: Creates a list of buttons corresponding to each object in the simulation
     * and adds it to the mainPanel.
     * 
     * @param start is the first element in simualtions Objects for which to start
     * making buttons for.
     */
    protected void initializeButtons(Integer start) {
        for (int i = start; i < simulation.getNumberOfObjects(); i++) {
            mainPanel.add(createButton(i));
        }
    }

    /**
     * EFFECTS: Creates button for Object.
     * 
     * @param i index of Object to create a button for.
     */
    private JButton createButton(int i) {
        JButton button;
        if (i == 0) {
            button = new JButton("Stationary Reference Frame ");
        } else {
            button = new JButton("Object " + i);
        }
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction(i);
            }
        });
        return button;
    }

    /**
     * EFFECTS: action to occur when a button for Object i is pressed.
     * 
     * @param i index of Object button to assign action to.
     */
    protected abstract void buttonAction(Integer i);

}
