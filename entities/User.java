package entities;
import java.lang.*;

public class User {
    protected String userId, name, email, gender, password;
    protected int age, role;

    public User() {
    }

    public User(String name, String userId, String email, String gender, int role, int age, String password) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.age = age;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", role=" + role +
                '}';
    }
}