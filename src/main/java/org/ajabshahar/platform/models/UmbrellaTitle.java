package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "UMBRELLA_TITLE")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.UmbrellaTitle.findAll",
                query = "SELECT p FROM UmbrellaTitle p"
        )
})
public class UmbrellaTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE",nullable = true)
    private String title;

    @Column(name = "ENGLISH_TRANSLATION",nullable = true)
    private String englishTranslation;

    @Column(name = "ENGLISH_TRANSLITERATION", nullable = true)
    private String englishTransliteration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }

    public String getEnglishTransliteration() {
        return englishTransliteration;
    }

    public void setEnglishTransliteration(String englishTransliteration) {
        this.englishTransliteration = englishTransliteration;
    }
}
