package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "COUPLET")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Couplet.findAll",
                query = "SELECT p FROM Couplet p where show_on_landingpage = 'yes'"
        )
})
public class Couplet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String DESCRIPTION;

    @Column(name = "CATEGORY", nullable = false)
    private String CATEGORY;


    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private String showOnLandingPage;



    public long getId() {
        return id;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION= DESCRIPTION;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY=CATEGORY;
    }


    public String getShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(String showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }

}
