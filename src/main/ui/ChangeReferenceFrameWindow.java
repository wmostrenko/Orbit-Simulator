package ui;

import model.Simulation;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;

public class ChangeReferenceFrameWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Simulation simulation;

    private static int WHITESPACE = 7;

    public ChangeReferenceFrameWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    public void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Get Object Properties");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 200);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        initializeMainPanel();
        this.frame.setVisible(true);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 0));
        mainPanel.setBackground(Color.WHITE);
        initializeObjectButtons();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));

        frame.add(mainPanel);
    }

    private void initializeObjectButtons() {
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            mainPanel.add(createButton(i));
        }
    }

    private JButton createButton(int index) {
        JButton button;

        if (index == 0) {
            button = new JButton("Stationary Reference Frame ");
        } else {
            button = new JButton("Object " + index);
        }

        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.setCurrentReferenceFrame(simulation.getObjectAt(index));
            }
        });
        return button;
    }
}
