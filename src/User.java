public class User {

    private final String name;
    private final String surname;
    private final long PESEL;
    private final String gender;
    private final String birthDate;
    private final String email;
    private final int phoneNumber;

    User(String name, String surname, long PESEL, String birthDate, String email, int phoneNumber, String gender){
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
