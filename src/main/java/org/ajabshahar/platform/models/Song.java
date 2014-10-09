package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "SONG")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Song.findAll",
                query = "SELECT p FROM Song p"
        )
})
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE_ID", nullable = true)
    private Title title;

    @Column(name = "SINGER_ID", nullable = true)
    private Person singer;

    @Column(name = "POET_ID", nullable = true)
    private Person poet;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = true)
    private Boolean showOnLandingPage;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "DURATION", nullable = true)
    private String duration;

    public long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Person getSinger() {
        return singer;
    }

    public void setSinger(Person singer) {
        this.singer = singer;
    }

    public Person getPoet() {
        return poet;
    }

    public void setPoet(Person poet) {
        this.poet = poet;
    }

    public Boolean getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(Boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
