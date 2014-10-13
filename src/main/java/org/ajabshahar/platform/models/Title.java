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
    private String original;
    private String english_Translation;
    private String english_Transliteration;

    @Column(name = "ORIGINAL", nullable = false)
    public String getOriginalTitle() {
        return original;
    }

    public void setOriginalTitle(String Original) {
        this.original = Original;
    }

    @Column(name = "ENGLISH_TRANSLATION", nullable = false)
    public String getEnglishTranslationTitle() {
        return english_Translation;
    }

    public void setEnglishTranslationTitle(String ENGLISH_TRANSLATION) {
        this.english_Translation = ENGLISH_TRANSLATION;
    }

    @Column(name = "ENGLISH_TRANSLITERATION", nullable = false)
    public String getEnglishTransliterationTitle() {
        return english_Transliteration;
    }

    public void setEnglishTransliterationTitle(String ENGLISH_TRANSLITERATION) {
        this.english_Transliteration = ENGLISH_TRANSLITERATION;
    }
}
