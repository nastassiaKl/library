package by.kalinklish.project.entity;

public class User extends Entity {
    private int numberTicket;
    private String surname;
    private String name;
    private String middleName;
    private int age;
    private String phoneNumber;
    private String mail;
    private String login;
    private String password;
    private String profilePhoto;

    public User() {}

    public User(int id, int numberTicket, String surname, String name, String middleName, int age, String phoneNumber, String mail, String login, String password, String profilePhoto) {
        super(id);
        this.numberTicket = numberTicket;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public User(int id, String surname, String name, String middleName, String login) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.login = login;
    }

    public User(int id, String surname, String name, String middleName, String login, String password) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(int numberTicket) {
        this.numberTicket = numberTicket;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (numberTicket != user.numberTicket) return false;
        if (age != user.age) return false;
        if (!surname.equals(user.surname)) return false;
        if (!name.equals(user.name)) return false;
        if (!middleName.equals(user.middleName)) return false;
        if (!phoneNumber.equals(user.phoneNumber)) return false;
        if (!mail.equals(user.mail)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        return profilePhoto.equals(user.profilePhoto);
    }

    @Override
    public int hashCode() {
        int result = numberTicket;
        result = 31 * result + surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + age;
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + mail.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + profilePhoto.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", numberTicket=" + numberTicket +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
