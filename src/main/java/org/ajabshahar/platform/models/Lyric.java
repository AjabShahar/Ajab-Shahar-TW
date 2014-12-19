package org.ajabshahar.platform.models;


import javax.persistence.*;

public class Lyric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CHORUS", nullable = false)
    private String chorus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SONG_ID")
    private Song song;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUPLET_ID")
    private Couplet couplet;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "STANZA_ID")
    private Stanza stanza;

    public String getChorus() {
        return chorus;
    }

    public void setChorus(String chorus) {
        this.chorus = chorus;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Couplet getCouplet() {
        return couplet;
    }

    public void setCouplet(Couplet couplet) {
        this.couplet = couplet;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
