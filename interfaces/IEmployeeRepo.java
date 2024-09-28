package interfaces;

import entities.Employee;

import java.util.List;

public interface IEmployeeRepo {
    public void addEmployee(Employee e);
    public void updateEmployee(Employee e);
    public void deleteEmployee(String id);
    public Employee searchEmployee(String id);
    public List<Employee> getAllEmployee();
}
