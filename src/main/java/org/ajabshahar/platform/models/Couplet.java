package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "COUPLET")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Couplet.findAll",
                query = "SELECT p FROM Couplet p"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Couplet.findAllOnLandingPage",
                query = "SELECT p FROM Couplet p where p.showOnLandingPage = true"
        )
})
public class Couplet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL_TITLE", nullable = false)
    private String originalTitle;

    @Column(name = "ENGLISH_TRANSLATION", nullable = false)
    private String englishTranslation;

    @Column(name = "ENGLISH_TRANSLITERATION", nullable = false)
    private String englishTransliteration;

    @Column(name = "THUMBNAIL_URL", nullable = false)
    private String thumbnailUrl;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    @Column(name = "ORIGINAL_TEXT", nullable = false)
    private  String originalText;

    @Column(name = "ENGLISH_TRANSLATION_TEXT",nullable = true)
    private String englishTranslationText;

    @Column(name ="ENGLISH_TRANSLITERATION_TEXT", nullable = true)
    private String englishTransliterationText;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "POET_ID")
    private PersonDetails poet;

    public long getId() {
        return id;
    }

    public boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PersonDetails getPoet() {
        return poet;
    }

    public void setPoet(PersonDetails poet) {
        this.poet = poet;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getEnglishTranslationText() {
        return englishTranslationText;
    }

    public void setEnglishTranslationText(String englishTranslationText) {
        this.englishTranslationText = englishTranslationText;
    }

    public String getEnglishTransliterationText() {
        return englishTransliterationText;
    }

    public void setEnglishTransliterationText(String englishTransliterationText) {
        this.englishTransliterationText = englishTransliterationText;
    }

    public void setId(long id) {
        this.id = id;
    }
}
