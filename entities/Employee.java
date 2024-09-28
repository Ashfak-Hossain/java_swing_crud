package entities;

public class Employee extends User {
    private double salary;
    private int bonus;

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return userId + "," + name + "," + email + "," + gender + "," + role + "," + age + "," + password + "," + salary + "," + bonus;
    }

    public Employee(String userId, String name, String email, String gender, int role, int age, String password, double salary, int bonus) {
        super(userId, name, email, gender, role, age, password);
        this.salary = salary;
        this.bonus = bonus;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getBonus() {
        return this.bonus;
    }

    // Method to parse an Employee object from a string
    public static Employee formEmployee(String str) {
        try {
            String[] info = str.split(",");
            if (info.length != 9) {
                throw new IllegalArgumentException("Invalid input format, expected 9 fields but got " + info.length);
            }

            return new Employee(
                    info[0],  // userId
                    info[1],  // name
                    info[2],  // email
                    info[3],  // gender
                    Integer.parseInt(info[4]),  // role
                    Integer.parseInt(info[5]),  // age
                    info[6],  // password
                    Double.parseDouble(info[7]),  // salary
                    Integer.parseInt(info[8])  // bonus
            );
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric fields in employee data: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error creating Employee from string: " + e.getMessage());
            return null;
        }
    }
}
