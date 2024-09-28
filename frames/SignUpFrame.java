package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

import entities.*;
import repositories.*;

public class SignUpFrame extends JFrame implements ActionListener {
    private JLabel userIdLabel, nameLabel, emailLabel, genderLabel, ageLabel, passLabel;
    private JTextField userTF, nameTF, emailTF, ageTF;
    private JPasswordField passPF;
    private JRadioButton maleRB, femaleRB;
    private ButtonGroup bg;
    private JButton signUpBtn, backBtn;

    private JPanel panel;

    public SignUpFrame() {
        super("Admin Operation Frame");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        // User Id
        this.userIdLabel = new JLabel("User Id:");
        this.userIdLabel.setBounds(50, 50, 60, 30);
        this.panel.add(userIdLabel);

        this.userTF = new JTextField();
        this.userTF.setBounds(120, 50, 100, 30);
        this.panel.add(userTF);

        // name
        this.nameLabel = new JLabel("Name:");
        this.nameLabel.setBounds(50, 100, 60, 30);
        this.panel.add(nameLabel);

        this.nameTF = new JTextField();
        this.nameTF.setBounds(120, 100, 100, 30);
        this.panel.add(nameTF);

        // Email
        this.emailLabel = new JLabel("email:");
        this.emailLabel.setBounds(50, 150, 60, 30);
        this.panel.add(emailLabel);

        this.emailTF = new JTextField();
        this.emailTF.setBounds(120, 150, 100, 30);
        this.panel.add(emailTF);

        // Password
        this.passLabel = new JLabel("Password:");
        this.passLabel.setBounds(50, 200, 100, 30);
        this.panel.add(passLabel);

        this.passPF = new JPasswordField();
        this.passPF.setBounds(120, 200, 100, 30);
        this.panel.add(passPF);

        //Gender
        this.genderLabel = new JLabel("Gender:");
        this.genderLabel.setBounds(50, 250, 60, 30);
        this.panel.add(genderLabel);

        this.maleRB = new JRadioButton("Male");
        this.maleRB.setBounds(120, 250, 80, 30);
        this.panel.add(maleRB);

        this.femaleRB = new JRadioButton("Female");
        this.femaleRB.setBounds(190, 250, 80, 30);
        this.panel.add(femaleRB);

        this.bg = new ButtonGroup();
        bg.add(maleRB);
        bg.add(femaleRB);

        // Age
        this.ageLabel = new JLabel("Age:");
        this.ageLabel.setBounds(50, 300, 60, 30);
        this.panel.add(ageLabel);

        this.ageTF = new JTextField();
        this.ageTF.setBounds(120, 300, 100, 30);
        this.panel.add(ageTF);


        //? Submit Button
        this.signUpBtn = new JButton("Submit");
        this.signUpBtn.setBounds(50, 350, 100, 30);
        this.signUpBtn.addActionListener(this);
        this.panel.add(signUpBtn);


        // Back button
        this.backBtn = new JButton("back");
        this.backBtn.setBounds(160, 350, 100, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        //  Back Button
        if (command.equals(backBtn.getText())) {
            LoginFrame loginFrame = new LoginFrame();
            this.setVisible(false);
            loginFrame.setVisible(true);
        }

        if (command.equals(signUpBtn.getText())) {
            String userId, name, email, gender, pass;
            int age, role;

            //validate all fields are filled
            if ((!userTF.getText().isEmpty()) &&
                    (!nameTF.getText().isEmpty()) &&
                    (!emailTF.getText().isEmpty()) &&
                    (maleRB.isSelected() || femaleRB.isSelected()) &&
                    (!ageTF.getText().isEmpty()) &&
                    (!Arrays.toString(passPF.getPassword()).isEmpty())) {
                userId = userTF.getText();
                EmployeeRepo erp = new EmployeeRepo();
                User user = erp.searchEmployee(userId);
                if (user == null) {
                    try {
                        name = nameTF.getText();
                        email = emailTF.getText();
                        if (maleRB.isSelected()) {
                            gender = maleRB.getText();
                        } else {
                            gender = femaleRB.getText();
                        }
                        age = Integer.parseInt(ageTF.getText());
                        pass = passPF.getText();

                        // role 1 == admin, role 2 == employee
                        role = 2;

                        Employee e = new Employee(userId, name, email, gender, role, age, pass, 0.0, 0);
                        erp.addEmployee(e);

                        JOptionPane.showMessageDialog(this, "Employee added successfully");

                        LoginFrame loginFrame = new LoginFrame();
                        this.setVisible(false);
                        loginFrame.setVisible(true);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User Id already exists.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "please fill up all the field properly");
            }
        }
    }
}