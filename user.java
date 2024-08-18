package Bean;
import java.io.Serializable;

public class user implements Serializable {
    String email;
    String password;

    public user(String email, String password) {

        this.email = email;
        this.password = password;

    }

    public user() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


