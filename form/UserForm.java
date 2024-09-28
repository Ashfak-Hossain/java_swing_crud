package form;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import entities.Employee; // Ensure the Employee entity is imported
import repositories.EmployeeRepo; // Ensure the EmployeeRepo is imported

public class UserForm extends JDialog {
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<String> roleComboBox; // Dropdown for roles
    private JTextField ageField;
    private JTextField salaryField;
    private JTextField bonusField;
    private boolean isUpdate;
    private EmployeeRepo employeeRepo;

    public UserForm(JFrame parent, boolean isUpdate, Employee employee) {
        super(parent, isUpdate ? "Update User" : "Add User", true);
        this.isUpdate = isUpdate;
        this.employeeRepo = new EmployeeRepo();
        initializeComponents();

        if (isUpdate && employee != null) {
            populateFields(employee);
        }

        setSize(400, 400);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void initializeComponents() {
        // Create a JPanel to hold all components and set padding
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        userIdField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();

        // Create radio buttons for gender selection
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Create a JComboBox for roles
        String[] roles = {"Admin", "Employee"};
        roleComboBox = new JComboBox<>(roles);

        ageField = new JTextField();
        salaryField = new JTextField();
        bonusField = new JTextField();

        JButton submitButton = new JButton(isUpdate ? "Update" : "Add");
        submitButton.addActionListener(e -> handleSubmit());

        // Add components to the panel
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        panel.add(genderPanel);
        panel.add(new JLabel("Role:"));
        panel.add(roleComboBox);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Bonus:"));
        panel.add(bonusField);
        panel.add(new JLabel(""));
        panel.add(submitButton);

        // Add the panel to the dialog
        add(panel);
    }

    private void populateFields(Employee employee) {
        userIdField.setText(employee.getUserId());
        nameField.setText(employee.getName());
        emailField.setText(employee.getEmail());

        // Set selected gender radio button
        if ("Male".equalsIgnoreCase(employee.getGender())) {
            maleRadioButton.setSelected(true);
        } else {
            femaleRadioButton.setSelected(true);
        }

        // Set selected role in the dropdown
        roleComboBox.setSelectedItem(employee.getRole() == 1 ? "Admin" : "Employee");

        ageField.setText(String.valueOf(employee.getAge()));
        salaryField.setText(String.valueOf(employee.getSalary()));
        bonusField.setText(String.valueOf(employee.getBonus()));
    }

    private void handleSubmit() {
        // Collect data from fields
        String userId = userIdField.getText();
        String name = nameField.getText();
        String email = emailField.getText();

        // Determine selected gender
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";

        // Get selected role
        int role = Objects.equals(roleComboBox.getSelectedItem(), "Admin") ? 1 : 2;

        int age = Integer.parseInt(ageField.getText());
        double salary = Double.parseDouble(salaryField.getText());
        int bonus = Integer.parseInt(bonusField.getText());

        Employee employee = new Employee(userId, name, email, gender, role, age, "", salary, bonus);

        if (isUpdate) {
            employeeRepo.updateEmployee(employee);
        } else {
            employeeRepo.addEmployee(employee);
        }
        dispose();
    }
}
