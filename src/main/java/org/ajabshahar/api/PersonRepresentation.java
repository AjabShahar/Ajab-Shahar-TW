package org.ajabshahar.api;

import lombok.Getter;
import org.ajabshahar.platform.models.Category;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;

@Getter
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
    private boolean publish;

    public PersonRepresentation(){

    }
    public PersonRepresentation(long id, String firstName, String middleName, String lastName, String firstNameInHindi, String middleNameInHindi, String lastNameInHindi, Set<String> roles, Category primaryOccupation, String thumbnailURL, String profile, boolean publish) {
        this.id = id;
        this.firstNameInHindi = firstNameInHindi;
        this.middleNameInHindi = middleNameInHindi;
        this.lastNameInHindi = lastNameInHindi;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles = roles;
        this.primaryOccupation = primaryOccupation;
        this.thumbnailURL = thumbnailURL;
        this.profile = profile;
        this.publish = publish;
    }

    @Override
    public String toString() {
        return format("first name: %s, middle name: %2s, last name: %3s", getFirstName(), getMiddleName(), getLastName());
    }

    public void setPrimaryOccupation(Category primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }
}
