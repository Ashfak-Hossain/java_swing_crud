package frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeFrame extends JFrame implements ActionListener {
    public EmployeeFrame() {
        super("Employee Frame");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Add components or layout for Employee dashboard
        JLabel welcomeLabel = new JLabel("Welcome, Employee!");
        welcomeLabel.setBounds(200, 50, 200, 30);
        this.add(welcomeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle employee-specific actions
    }
}