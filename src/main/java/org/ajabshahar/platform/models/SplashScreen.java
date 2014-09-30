package org.ajabshahar.platform.models;

@Entity
@Table(name = "SPLASH_SCREEN")
@NamedQueries({
        @NamedQuery(
                name = "com.example.helloworld.core.SplashScreen.findAll",
                query = "SELECT p FROM SPLASH_SCREEN p"
        )
})
public class SplashScreen {
	  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "URL", nullable = true)
    private String URL;

    @Column(name = "IMAGE_URL", nullable = true)
    private String IMAGE_URL;

    @Column(name = "FORMAT", nullable = false)
    private String FORMAT;

    public long getId() {
        return id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getImageURL() {
        return IMAGE_URL;
    }

    public void setImage_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getFormat() {
        return FORMAT;
    }

    public void setFormat(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        final Person that = (Person) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.fullName, that.fullName) &&
                Objects.equals(this.jobTitle, that.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, jobTitle);
    }
}