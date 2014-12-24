package org.ajabshahar.api;


import org.ajabshahar.platform.models.Couplet;
import org.ajabshahar.platform.models.Stanza;

public class LyricsSummaryRepresentation {

    private int id;
    private Couplet couplet;
    private Stanza stanza;
    private int sequenceNumber;
    private String ContentType;

    public LyricsSummaryRepresentation(int id, Couplet couplet, Stanza stanza, int sequenceNumber, String contentType) {
        this.id = id;
        this.couplet = couplet;
        this.stanza = stanza;
        this.sequenceNumber = sequenceNumber;
        this.ContentType = contentType;
    }

    public int getId() {
        return id;
    }

    public Couplet getCouplet() {
        return couplet;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getContentType() {
        return ContentType;
    }
}
