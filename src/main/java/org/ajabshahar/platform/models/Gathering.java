package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "GATHERING")
@NamedQueries({
    @NamedQuery(
            name = "org.ajabshahar.platform.models.Gathering.findAll",
            query = "SELECT p FROM Gathering p"
    )
})
public class Gathering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hindi_text", nullable = true)
    private String hindi;

    @Column(name = "english_text", nullable = false)
    private String english;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHindi() {
        return hindi;
    }

    public void setHindi(String hindiText) {
        this.hindi = hindiText;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String englishText) {
        this.english = englishText;
    }
}
