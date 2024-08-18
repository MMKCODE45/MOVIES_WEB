package Bean;

import java.io.Serializable;

public class Admin extends user implements Serializable {
    private String adminID;
    private String firstName;
    private String lastname;

    public Admin() {
    }

    public Admin(String adminID, String firstName, String lastname, String email, String password) {
        super(email, password);
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
