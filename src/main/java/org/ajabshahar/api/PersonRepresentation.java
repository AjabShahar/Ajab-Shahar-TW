package org.ajabshahar.api;

import org.ajabshahar.platform.models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

public class PersonRepresentation {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<String> roles;

    public PersonRepresentation(long id, String firstName, String middleName, String lastName, List<String> roles) {
        this.id = id;
        this.roles = new ArrayList<>();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String toString() {
        return format("first name: %s, middle name: %2s, last name: %3s", getFirstName(), getMiddleName(), getLastName());
    }

    public long getId() {
        return id;
    }
}
