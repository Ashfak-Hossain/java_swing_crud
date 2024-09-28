package repositories;

import entities.Employee;
import interfaces.IEmployeeRepo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo implements IEmployeeRepo {
    private final String file = "repositories/data/employees.txt";
    private final FileIO fio = new FileIO();

    @Override
    public void addEmployee(Employee e) {
        // Load current employees
        List<Employee> employees = this.getAllEmployee();

        // Add the new employee
        employees.add(e);

        // Write back the updated list
        writeEmployeesToFile(employees);
    }

    @Override
    public void updateEmployee(Employee e) {
        List<Employee> employees = this.getAllEmployee();

        System.out.println(e);

        boolean updated = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getUserId().equals(e.getName())) {
                employees.set(i, e);
                updated = true;
                break;
            }
        }
        if (updated) {
            writeEmployeesToFile(employees);
        } else {
            System.out.println("Employee with ID " + e.getUserId() + " not found.");
        }
    }


    @Override
    public void deleteEmployee(String id) {
        List<Employee> employees = this.getAllEmployee();

        // Find and remove the employee with the given ID
        employees.removeIf(employee -> employee.getUserId().equals(id));

        // Write the updated list back to the file
        writeEmployeesToFile(employees);
    }

    @Override
    public Employee searchEmployee(String id) {
        List<Employee> employees = this.getAllEmployee();

        // Search for employee by ID
        for (Employee employee : employees) {
            if (employee != null && employee.getUserId().equals(id)) {
                return employee;
            }
        }
        return null; // Return null if not found
    }

    @Override
    public List<Employee> getAllEmployee() {
        String[] data = fio.readFile(this.file);
        List<Employee> employees = new ArrayList<>();

        // Populate the employee list from file data
        for (String line : data) {
            if (line != null) {
                Employee employee = Employee.formEmployee(line);
                employees.add(employee);
            }
        }

        return employees;
    }

    // Helper method to write list of employees to the file
    private void writeEmployeesToFile(List<Employee> employees) {
        String[] data = new String[employees.size()];

        // Convert employees to string format
        for (int i = 0; i < employees.size(); i++) {
            data[i] = employees.get(i).toString();
        }

        // Write to file
        fio.writeFile(this.file, data);
    }
}
