package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "GENRE")
@NamedQueries({
    @NamedQuery(
            name = "org.ajabshahar.platform.models.Genre.findAll",
            query = "SELECT p FROM Genre p"
    )
})
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL_TEXT", nullable = false)
    private String originalText;

    @Column(name = "ENGLISH_TEXT", nullable = false)
    private String englishText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal() {
        return originalText;
    }

    public void setOriginal(String originalText) {
        this.originalText = originalText;
    }

    public String getEnglish() {
        return englishText;
    }

    public void setEnglish(String englishText) {
        this.englishText = englishText;
    }
}
