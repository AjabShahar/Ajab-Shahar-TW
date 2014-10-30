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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "synonym", nullable = false)
    private String synonym;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }
}
