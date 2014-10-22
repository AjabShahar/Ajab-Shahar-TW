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
        ),
})

public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String NAME;

    @Column(name = "SYNONYM", nullable = false)
    private String SYNONYM;

    @Column(name = "CATEGORY", nullable = false)
    private String CATEGORY;

    @Column(name = "SHOW_ON_LANDING_PAGE", nullable = false)
    private boolean showOnLandingPage;

    public long getId() {
        return id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSYNONYM() {
        return SYNONYM;
    }

    public void setSYNONYM(String SYNONYM) {
        this.SYNONYM=SYNONYM;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY=CATEGORY;
    }


    public boolean isShowOnLandingPage() {
        return showOnLandingPage;
    }

    public void setShowOnLandingPage(boolean showOnLandingPage) {
        this.showOnLandingPage = showOnLandingPage;
    }
}
