package org.ajabshahar.api;


public class SongTextSummaryRepresentation {

    private int id;
    private SongTextContentSummaryRepresentation songTextContent;
    private int sequenceNumber;

    public SongTextSummaryRepresentation(int id, SongTextContentSummaryRepresentation songTextContent, int sequenceNumber) {
        this.id = id;
        this.songTextContent = songTextContent;
        this.sequenceNumber = sequenceNumber;
    }

    public int getId() {
        return id;
    }


    public Object getSongTextContent() {
        return songTextContent;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

}
