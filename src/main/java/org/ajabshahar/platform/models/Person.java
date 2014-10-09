package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Person.findAll",
                query = "SELECT p FROM Person p"
        )
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String FIRST_NAME;

    @Column(name = "MIDDLE_NAME", nullable = true)
    private String MIDDLE_NAME;

    @Column(name = "LAST_NAME", nullable = false)
    private String LAST_NAME;

    public String getFirstName() {
        return FIRST_NAME;
    }

    public void setFirstName(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getMiddleName() {
        return MIDDLE_NAME;
    }

    public void setMiddleName(String MIDDLE_NAME) {
        this.MIDDLE_NAME = MIDDLE_NAME;
    }

    public String getLastName() {
        return LAST_NAME;
    }

    public void setLastName(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }
}
