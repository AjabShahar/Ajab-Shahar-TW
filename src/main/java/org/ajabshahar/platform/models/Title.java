package org.ajabshahar.platform.models;

import javax.persistence.*;

@Entity
@Table(name = "TITLE")
@NamedQueries({
        @NamedQuery(
                name = "org.ajabshahar.platform.models.Title.findAll",
                query = "SELECT p FROM Title p"
        )
})
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
