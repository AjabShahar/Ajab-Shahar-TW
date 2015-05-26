package org.ajabshahar.platform.models;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

import static java.lang.String.format;

@Entity
@Getter
@Setter
@Table(name = "PERSON")
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


    public String getName() {
        return format("%s %2s %3s", Strings.nullToEmpty(getFirstName()), Strings.nullToEmpty(getMiddleName()), Strings.nullToEmpty(getLastName())).replaceAll("\\s+", " ").trim();
    }


    public String getHindiName() {
        return format("%s %2s %3s", Strings.nullToEmpty(getFirstNameInHindi()), Strings.nullToEmpty(getMiddleNameInHindi()), Strings.nullToEmpty(getLastNameInHindi())).replaceAll("\\s+", " ").trim();
    }

}