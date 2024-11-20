package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Simulation;

public abstract class ButtonWindow extends SubWindow {
    
    public ButtonWindow(String title, Integer width, Integer height, Simulation simulation) {
        super(title, width, height, simulation);
    }

    protected void initializeButtons(Integer start) {
        for (int i = start; i < simulation.getNumberOfObjects(); i++) {
            mainPanel.add(createButton(i));
        }
    }

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

    protected abstract void buttonAction(Integer i);

}
