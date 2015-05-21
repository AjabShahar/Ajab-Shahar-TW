package org.ajabshahar.api;

import org.ajabshahar.platform.models.Category;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;

public class PersonRepresentation {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String firstNameInHindi;
    private String middleNameInHindi;
    private String lastNameInHindi;
    private Set<String> roles;
    private Category primaryOccupation;
    private String thumbnailURL;
    private String profile;

    public PersonRepresentation(long id, String firstName, String middleName, String lastName, String firstNameInHindi, String middleNameInHindi, String lastNameInHindi, Set<String> roles, Category primaryOccupation, String thumbnailURL, String profile) {
        this.id = id;
        this.firstNameInHindi = firstNameInHindi;
        this.middleNameInHindi = middleNameInHindi;
        this.lastNameInHindi = lastNameInHindi;
        this.roles = new LinkedHashSet<>();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles = roles;
        this.primaryOccupation = primaryOccupation;
        this.thumbnailURL = thumbnailURL;
        this.profile = profile;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<String> getRoles() {
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

    public String getFirstNameInHindi() {
        return firstNameInHindi;
    }

    public String getMiddleNameInHindi() {
        return middleNameInHindi;
    }

    public String getLastNameInHindi() {
        return lastNameInHindi;
    }

    public Category getPrimaryOccupation() {
        return primaryOccupation;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getProfile() {
        return profile;
    }

    public void setPrimaryOccupation(Category primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }
}
