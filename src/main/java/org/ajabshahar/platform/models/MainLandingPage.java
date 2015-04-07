package org.ajabshahar.platform.models;

import java.util.List;

public class MainLandingPage {
    private List<Song> songs;
    private List<Couplet> couplets;
    private List<Word> words;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Couplet> getCouplets() {
        return couplets;
    }

    public void setCouplets(List<Couplet> couplets) {
        this.couplets = couplets;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
