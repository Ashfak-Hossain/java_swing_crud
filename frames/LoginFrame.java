package frames;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel emailLabel, passLabel;
    private JTextField userTF; // This will now represent email
    private JPasswordField passPF;
    private JButton loginBtn, exitBtn, signUpBtn;
    private JPanel panel;

    public LoginFrame() {
        super("Login Frame");
        this.setSize(700, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.emailLabel = new JLabel("Email: ");
        this.emailLabel.setBounds(250, 50, 60, 20);
        this.panel.add(emailLabel);

        this.userTF = new JTextField();
        this.userTF.setBounds(300, 45, 200, 30);
        this.panel.add(userTF);

        this.passLabel = new JLabel("Password:");
        this.passLabel.setBounds(235, 90, 800, 20);
        this.panel.add(passLabel);

        this.passPF = new JPasswordField();
        this.passPF.setBounds(300, 85, 200, 30);
        this.panel.add(passPF);

        this.loginBtn = new JButton("Login");
        this.loginBtn.setBounds(235, 130, 100, 30);
        this.loginBtn.addActionListener(this);
        this.panel.add(loginBtn);

        this.signUpBtn = new JButton("Sign Up");
        this.signUpBtn.setBounds(350, 130, 130, 30);
        this.signUpBtn.addActionListener(this);
        this.panel.add(signUpBtn);

        this.exitBtn = new JButton("Exit");
        this.exitBtn.setBounds(240, 170, 60, 30);
        this.exitBtn.addActionListener(this);
        this.panel.add(exitBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals(loginBtn.getText())) {
            // Handle login button
            String email = userTF.getText();
            String password = new String(passPF.getPassword());

            if (validateUser(email, password)) {
                // User is valid, open the appropriate frame
                if (isEmployee(email)) {
                    EmployeeFrame employeeFrame = new EmployeeFrame();
                    this.setVisible(false);
                    employeeFrame.setVisible(true);
                } else {
                    // If it's not an employee, assume it's an admin
                    AdminFrame adminFrame = new AdminFrame();
                    this.setVisible(false);
                    adminFrame.setVisible(true);
                }
            } else {
                // Show message if account not found
                JOptionPane.showMessageDialog(this, "Account not found or invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals(signUpBtn.getText())) {
            SignUpFrame suf = new SignUpFrame();
            this.setVisible(false);
            suf.setVisible(true);
        } else if (command.equals(exitBtn.getText())) {
            // Handle exit button
            System.exit(0);
        }
    }

    private boolean validateUser(String email, String password) {
        // Check in both employee and admin files
        return checkCredentialsInFile("repositories/data/employees.txt", email, password) ||
                checkCredentialsInFile("repositories/data/admins.txt", email, password);
    }

    private boolean checkCredentialsInFile(String filePath, String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Assuming each line is formatted as "userId,name,email,gender,role,age,password,salary,bonus"
                if (parts.length >= 7) { // Updated to check against email
                    String fileEmail = parts[2].trim(); // Email is now at index 2
                    String filePassword = parts[6].trim(); // Password is now at index 6
                    if (fileEmail.equals(email) && filePassword.equals(password)) {
                        return true; // Valid credentials found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // No valid credentials found
    }

    private boolean isEmployee(String email) {
        // Check if the email exists in the employee file
        try (BufferedReader br = new BufferedReader(new FileReader("repositories/data/employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Assuming each line is formatted as "userId,name,email,gender,role,age,password,salary,bonus"
                if (parts.length >= 3) {
                    String fileEmail = parts[2].trim(); // Email is now at index 2
                    if (fileEmail.equals(email)) {
                        return true; // User email found in employee file
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Email not found in employee file
    }
}
