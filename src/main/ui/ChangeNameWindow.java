package ui;

import model.Simulation;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;

public class ChangeNameWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private Simulation simulation;
    JTextField textField;

    private static int WHITESPACE = 7;

    public ChangeNameWindow(Simulation simulation) {
        this.simulation = simulation;
        initializeFrame();
    }

    public void initializeFrame() {
        frame = new JFrame();
        this.frame.setTitle("Change Name");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(300, 100);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        initializeMainPanel();
        this.frame.setVisible(true);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 0));
        mainPanel.setBackground(Color.WHITE);
        initializeFieldPanels();
        initializeChangeNameButton();
        mainPanel.setBorder(BorderFactory.createMatteBorder(WHITESPACE, WHITESPACE, WHITESPACE, WHITESPACE, Color.WHITE));

        frame.add(mainPanel);
    }

    private void initializeFieldPanels() {
        mainPanel.add(createPanel());
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Name: ");
        panel.add(label);

        textField = new JTextField(simulation.getName());
        panel.add(textField);
        
        return panel;
    }

    private void initializeChangeNameButton() {
        JButton changeNameButton = createChangeNameButton();
        mainPanel.add(changeNameButton);
    }

    private JButton createChangeNameButton() {
        JButton button = new JButton("Change Name");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.changeName(textField.getText());
            }
        });
        return button;
    }
}
