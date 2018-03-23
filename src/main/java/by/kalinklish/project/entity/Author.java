package by.kalinklish.project.entity;

public class Author extends Entity {
    private String surname;
    private String name;
    private String middleName;
    private String countryBirth;

    public Author() {}

    public Author(int id) {
        super(id);
    }

    public Author(int id, String surname, String name, String middleName, String countryBirth) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.countryBirth = countryBirth;
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

    public String getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        this.countryBirth = countryBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (!surname.equals(author.surname)) return false;
        if (!name.equals(author.name)) return false;
        if (middleName != null ? !middleName.equals(author.middleName) : author.middleName != null) return false;
        return countryBirth.equals(author.countryBirth);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + countryBirth.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + getId() +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", countryBirth='" + countryBirth + '\'' +
                '}';
    }
}
