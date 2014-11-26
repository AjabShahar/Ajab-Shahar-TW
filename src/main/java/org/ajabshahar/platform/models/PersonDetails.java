package org.ajabshahar.platform.models;

import com.google.common.base.Strings;

import javax.persistence.*;

import static java.lang.String.format;

@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.PersonDetails.findAll",
                query = "SELECT p FROM PersonDetails p"
        )
})
public class PersonDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME", nullable = true)
    private String middleName;

    @Column(name = "LAST_NAME", nullable = true)
    private String lastName;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category= category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return format("%s %2s %3s", Strings.nullToEmpty(getFirstName()), Strings.nullToEmpty(getMiddleName()), Strings.nullToEmpty(getLastName())).replaceAll("\\s+", " ").trim();
    }
}