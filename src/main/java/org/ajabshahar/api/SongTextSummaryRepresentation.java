package org.ajabshahar.api;


import org.ajabshahar.platform.models.SongTextContent;

public class SongTextSummaryRepresentation {

    private int id;
    private SongTextContent songTextContent;
    private int sequenceNumber;

    public SongTextSummaryRepresentation(int id, SongTextContent songTextContent, int sequenceNumber) {
        this.id = id;
        this.songTextContent = songTextContent;
        this.sequenceNumber = sequenceNumber;
    }

    public int getId() {
        return id;
    }


    public SongTextContent getSongTextContent() {
        return songTextContent;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

}
