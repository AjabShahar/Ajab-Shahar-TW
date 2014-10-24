package org.ajabshahar.platform.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.PersonDetails.findAll",
                query = "SELECT p FROM PersonDetails p"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.PersonDetails.findSingers",
                query = "select p from PersonDetails p where p.category='Singer'"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.PersonDetails.findPoets",
                query = "select p from PersonDetails p where p.category='Poet'"
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
}