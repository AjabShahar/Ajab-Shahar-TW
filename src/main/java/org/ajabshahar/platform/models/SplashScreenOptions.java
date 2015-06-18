package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "SPLASH_SCREEN")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.SplashScreenOptions.findAll",
                query = "SELECT p FROM SplashScreenOptions p"
        )
})
public class SplashScreenOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "URL", nullable = true)
    private String URL;

    @Column(name = "IMAGE_URL", nullable = true)
    private String IMAGE_URL;

    @Column(name = "FORMAT", nullable = false)
    private String FORMAT;

    @Column(name = "NAME")
    private String NAME;

    public long getId() {
        return id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getImage() {
        return IMAGE_URL;
    }

    public void setImage(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getFormat() {
        return FORMAT;
    }

    public void setFormat(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getName() {
        return FORMAT;
    }

    public void setName(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return String.format("Url: %s, ImageUrl: %s, Format: %s", this.URL, this.IMAGE_URL, this.FORMAT);
    }
}