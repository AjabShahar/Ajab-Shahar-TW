package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.ajabshahar.platform.models.Category;
import org.ajabshahar.platform.models.PersonDetails;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;

public class PersonSummaryRepresentation {
    @Setter
    private long id;
    private String name;
    private String hindiName;
    private String primaryOccupation;
    private boolean publish;

    public PersonSummaryRepresentation() {
    }

    public PersonSummaryRepresentation(long id, String name, String hindiName, String primaryOccupation, boolean publish) {
        this.id = id;
        this.name = name;
        this.hindiName = hindiName;
        this.primaryOccupation = primaryOccupation;
        this.publish = publish;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return format("id: %s, name: %2s", getId(), getName());
    }

    @JsonProperty("hindiName")
    public String getHindiName() {
        return hindiName;
    }


    public static PersonSummaryRepresentation createFrom(PersonDetails personDetails) {
        if (personDetails != null) {
            Category primaryOccupation = personDetails.getPrimaryOccupation();
            String primaryOccupationName = (primaryOccupation != null) ? primaryOccupation.getName() : "";
            return new PersonSummaryRepresentation(personDetails.getId(), personDetails.getName(),
                    personDetails.getHindiName(), primaryOccupationName, personDetails.isPublish());
        }
        return null;
    }


    @JsonProperty("primaryOccupation")
    public String getPrimaryOccupation() {
        return primaryOccupation;
    }

    public static Set<PersonSummaryRepresentation> toPersonSummaries(Set<PersonDetails> people) {
        Set<PersonSummaryRepresentation> personSummaryRepresentations = null;
        if(people != null){
            personSummaryRepresentations = new LinkedHashSet<>();
            for (PersonDetails person : people) {
                personSummaryRepresentations.add(PersonSummaryRepresentation.createFrom(person));
            }
        }
        return personSummaryRepresentations;
    }

    public static Set<PersonDetails> toPeople(Set<PersonSummaryRepresentation> personSummaryRepresentations) {
        Set<PersonDetails> people = new LinkedHashSet<>();
        if (personSummaryRepresentations != null) {
            for (PersonSummaryRepresentation personSummaryRepresentation : personSummaryRepresentations) {
                PersonDetails personDetails = getPersonDetails(personSummaryRepresentation);
                people.add(personDetails);
            }
        }
        return people;
    }

    public static PersonDetails getPersonDetails(PersonSummaryRepresentation personSummaryRepresentation) {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(personSummaryRepresentation.getId());
        personDetails.setFirstName(personSummaryRepresentation.getName());
        return personDetails;
    }

    public boolean isPublish() {
        return publish;
    }
}
