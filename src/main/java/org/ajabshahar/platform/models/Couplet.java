package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "COUPLET")

public class Couplet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String DESCRIPTION;

    @Column(name = "CATEGORY", nullable = false)
    private String CATEGORY;


    @Column(name = "SHOW_ON_LANDINGPAGE", nullable = false)
    private String SHOW_ON_LANDINGPAGE;



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


    public String getSHOW_ON_LANDINGPAGE() {
        return SHOW_ON_LANDINGPAGE;
    }

    public void setSHOW_ON_LANDINGPAGE(String SHOW_ON_LANDINGPAGE) {
        this.SHOW_ON_LANDINGPAGE=SHOW_ON_LANDINGPAGE;
    }

}
