package org.ajabshahar.platform.models;

import com.google.common.base.Strings;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "FIRST_NAME_IN_HINDI", nullable = true)
    private String firstNameInHindi;

    @Column(name = "MIDDLE_NAME_IN_HINDI", nullable = true)
    private String middleNameInHindi;

    @Column(name = "LAST_NAME_IN_HINDI", nullable = true)
    private String lastNameInHindi;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "person_category", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRIMARY_OCCUPATION")
    private Category primaryOccupation;

    @Column(name = "THUMBNAIL_URL")
    private String thumbnailURL;

    @Column(name = "PROFILE")
    private String profile;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return format("%s %2s %3s", Strings.nullToEmpty(getFirstName()), Strings.nullToEmpty(getMiddleName()), Strings.nullToEmpty(getLastName())).replaceAll("\\s+", " ").trim();
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public String getFirstNameInHindi() {
        return firstNameInHindi;
    }

    public void setFirstNameInHindi(String firstNameInHindi) {
        this.firstNameInHindi = firstNameInHindi;
    }

    public String getMiddleNameInHindi() {
        return middleNameInHindi;
    }

    public void setMiddleNameInHindi(String middleNameInHindi) {
        this.middleNameInHindi = middleNameInHindi;
    }

    public String getLastNameInHindi() {
        return lastNameInHindi;
    }

    public void setLastNameInHindi(String lastNameInHindi) {
        this.lastNameInHindi = lastNameInHindi;
    }

    public String getHindiName() {
        return format("%s %2s %3s", Strings.nullToEmpty(getFirstNameInHindi()), Strings.nullToEmpty(getMiddleNameInHindi()), Strings.nullToEmpty(getLastNameInHindi())).replaceAll("\\s+", " ").trim();
    }

    public Category getPrimaryOccupation() {
        return primaryOccupation == null ? new Category() : primaryOccupation;
    }

    public void setPrimaryOccupation(Category primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}