package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "UMBRELLA_TITLE")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.UmbrellaTitle.findAll",
                query = "SELECT p FROM UmbrellaTitle p"
        )
})
public class UmbrellaTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE",nullable = true)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
