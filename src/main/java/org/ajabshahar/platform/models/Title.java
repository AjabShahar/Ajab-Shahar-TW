package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "TITLE")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Title.findAll",
                query = "SELECT p FROM Title p"
        )
})
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL", nullable = false)
    private String ORIGINAL;

    @Column(name = "ENGLISH_TRANSLATION", nullable = false)
    private String ENGLISH_TRANSLATION;

    @Column(name = "ENGLISH_TRANSLITERATION", nullable = false)
    private String ENGLISH_TRANSLITERATION;

    public String getOriginalTitle() {
        return ORIGINAL;
    }

    public void setOriginalTitle(String ORIGINAL) {
        this.ORIGINAL = ORIGINAL;
    }

    public String getEnglishTranslationTitle() {
        return ENGLISH_TRANSLATION;
    }

    public void setEnglishTranslationTitle(String ENGLISH_TRANSLATION) {
        this.ENGLISH_TRANSLATION = ENGLISH_TRANSLATION;
    }

    public String getEnglishTransliterationTitle() {
        return ENGLISH_TRANSLITERATION;
    }

    public void setEnglishTransliterationTitle(String ENGLISH_TRANSLITERATION) {
        this.ENGLISH_TRANSLITERATION = ENGLISH_TRANSLITERATION;
    }
}
