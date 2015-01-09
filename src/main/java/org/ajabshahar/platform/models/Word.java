package org.ajabshahar.platform.models;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "WORD_WORD_INTRODUCTION", joinColumns = @JoinColumn(name = "WORD_ID"),
//            inverseJoinColumns = @JoinColumn(name = "WORD_INTRODUCTION_ID"))
//    private Set<SongTextContent> wordIntroductions;

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

//    public Set<SongTextContent> getWordIntroductions() {
//        return wordIntroductions;
//    }
//
//    public void setWordIntroductions(Set<SongTextContent> wordIntroductions) {
//        this.wordIntroductions = wordIntroductions;
//    }
}
