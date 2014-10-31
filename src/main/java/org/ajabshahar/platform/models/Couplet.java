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

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CATEGORY", nullable = false)
    private String category;


    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;



    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public boolean isShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }
}
