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

    @Column(name = "original_text", nullable = false)
    private String original;

    @Column(name = "english_text", nullable = false)
    private String english;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String originalText) {
        this.original = originalText;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String englishText) {
        this.english = englishText;
    }
}
