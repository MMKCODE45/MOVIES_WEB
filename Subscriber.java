package Bean;

public class Subscriber extends user{


    private int subscriberID;
    private String firstName;
    private String lastname;
    private String preferedGenre;





    public Subscriber(int subscriberID, String firstName, String lastname, String preferedGenre, String email, String password) {
        super(email, password);

        this.subscriberID = subscriberID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.preferedGenre= preferedGenre;
        this.email=email;
        this.password= password;


    }

    public Subscriber() {
    }

    public int getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(int subscriberID) {
        this.subscriberID = subscriberID;
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

    public String getPreferedGenre() {
        return preferedGenre;
    }

    public void setPreferedGenre(String preferedGenre) {
        this.preferedGenre = preferedGenre;
    }
}
