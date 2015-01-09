package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "WORD")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Word.findAll",
                query = "SELECT p FROM Word p"
        ),
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Word.findAllOnLandingPage",
                query = "SELECT p FROM Word p where p.showOnLandingPage=true"
        )
})
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "WORD_OR_PHRASE", nullable = false)
    private String wordOrPhrase;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Column(name = "THUMBNAIL_URL", nullable = false)
    private String thumbnailUrl;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    public long getId() {
        return id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public String getWordOrPhrase() {
        return wordOrPhrase;
    }

    public void setWordOrPhrase(String wordOrPhrase) {
        this.wordOrPhrase = wordOrPhrase;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
