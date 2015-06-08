package org.ajabshahar.platform.models;


import javax.persistence.*;

@Entity
@Table(name = "SONG_TEXT")
public class SongText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORIGINAL")
    private String original;

    @Column(name = "TRANSLATION")
    private String translation;

    @Column(name = "TRANSLITERATION")
    private String transliteration;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

}
