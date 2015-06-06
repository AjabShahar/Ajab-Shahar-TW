package org.ajabshahar.platform.models;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Entity
@Table(name = "TITLE")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Title.findAll",
                query = "SELECT p FROM Title p"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Title.findSongTitles",
                query = "SELECT p FROM Title p WHERE p.category=(SELECT c from Category c where c.categoryType='Song Title')"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Title.findUmbrellaTitles",
                query = "SELECT p FROM Title p WHERE p.category=(SELECT c from Category c where c.categoryType='Umbrella Title')"
        )
})
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL_TITLE", nullable = true)
    private String originalTitle;

    @Column(name = "ENGLISH_TRANSLATION", nullable = true)
    private String englishTranslation;

    @Column(name = "ENGLISH_TRANSLITERATION", nullable = true)
    private String englishTransliteration;

    @BatchSize(size = 50)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Title() {

    }

    public Title(Title songTitle) {
        originalTitle = songTitle.getOriginalTitle();
        englishTranslation = songTitle.getEnglishTranslation();
        englishTransliteration = songTitle.getEnglishTransliteration();
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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
