package frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entities.Employee;
import form.UserForm;
import repositories.EmployeeRepo;

public class AdminFrame extends JFrame implements ActionListener {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private EmployeeRepo employeeRepo;

    public AdminFrame() {
        super("Admin Frame");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        employeeRepo = new EmployeeRepo();
        tableModel = new DefaultTableModel(new String[]{"User ID", "Name", "Email", "Gender", "Role", "Age", "Salary", "Bonus"}, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the scroll pane
        this.add(scrollPane, BorderLayout.CENTER);

        loadEmployeeData();

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the button panel
        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> addEmployee());

        JButton updateButton = new JButton("Update Employee");
        updateButton.addActionListener(e -> updateEmployee());

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(e -> deleteEmployee());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void addEmployee() {
        new UserForm(this, false, null);
        loadEmployeeData();
    }

    private void loadEmployeeData() {
        List<Employee> employees = employeeRepo.getAllEmployee();
        tableModel.setRowCount(0);

        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{
                    employee.getUserId(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.getGender(),
                    employee.getRole() == 1 ? "Admin" : "Employee",
                    employee.getAge(),
                    employee.getSalary(),
                    employee.getBonus()
            });
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            String userId = (String) tableModel.getValueAt(selectedRow, 0);
            Employee employee = employeeRepo.searchEmployee(userId);
            if (employee != null) {
                new UserForm(this, true, employee);
                loadEmployeeData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            String userId = (String) tableModel.getValueAt(selectedRow, 0);
            employeeRepo.deleteEmployee(userId);
            loadEmployeeData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions if needed
    }
}
