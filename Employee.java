package Bean;

import java.io.Serializable;

public class Employee extends user implements Serializable {


    private String EmployeeID;
    private String firstName;
    private String lastName;
    private String role;

    public Employee() {
    }

    public Employee(String employeeID, String firstName, String lastName, String role, String email, String password ){
        super(email, password);
        EmployeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password=password;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
