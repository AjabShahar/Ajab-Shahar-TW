package org.ajabshahar.api;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class PersonRepresentation {

    private String firstName;
    private String middleName;
    private String lastName;
    private List<String> roles;

    public PersonRepresentation(String firstName, String middleName, String lastName, String role) {
        this.roles = new ArrayList<>();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles.add(role);
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
}
