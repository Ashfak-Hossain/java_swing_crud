package entities;

public class Admin extends User {
    private String bonus;
    private String adminType;
    private String salary;
    
    public Admin() {
        super();
    }

    public Admin(String userId, String name, String email, String gender, int role, int age, String password, String adminType, String salary, String bonus) {
        super(userId, name, email, gender, role, age, password);
        this.adminType = adminType;
        this.salary = salary;
        this.bonus = bonus;
    }
    
    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }
    
    public String getAdminType() {
        return this.adminType;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return this.salary;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "bonus='" + bonus + '\'' +
                ", adminType='" + adminType + '\'' +
                ", salary='" + salary + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", role=" + role +
                '}';
    }

    public  Admin formAdmin(String str) {
        String[] info = str.split(",");
        Admin a = new Admin();
        a.setUserId(info[0]);
        a.setName(info[1]);
        a.setEmail(info[2]);
        a.setGender(info[3]);
        a.setRole(Integer.parseInt(info[4]));
        a.setAge(Integer.parseInt(info[5]));
        a.setPassword(info[6]);
        a.setAdminType(info[7]);
        a.setSalary(info[8]);
        a.setBonus(info[9]);
        return a;
    }
}