package org.ajabshahar.api;


public class UnitOfLyricsRepresentation {

    private String text;
    private String type;

    public UnitOfLyricsRepresentation(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public UnitOfLyricsRepresentation() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
