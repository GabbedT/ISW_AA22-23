package Database;

public class Employer extends User { 

    public Employer(String name, String surname, String email, String password, char gender) {
        super(1, name, surname, email, password, gender);
    }
}
