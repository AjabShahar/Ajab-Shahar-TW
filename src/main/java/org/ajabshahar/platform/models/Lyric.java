package org.ajabshahar.platform.models;


import javax.persistence.*;

@Entity
@Table(name = "LYRICS")
public class Lyric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CHORUS", nullable = false)
    private String chorus;

    @Column(name="SEQUENCEORDER",nullable = false)
    private int sequenceNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SONG_ID")
    private Song song;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUPLET_ID",nullable = false)
    private Couplet couplet;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "STANZA_ID",nullable = true)
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

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
