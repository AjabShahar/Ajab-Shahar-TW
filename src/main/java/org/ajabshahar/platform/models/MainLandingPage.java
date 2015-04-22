package org.ajabshahar.platform.models;

import java.util.Set;

public class MainLandingPage {
    private Set<Song> songs;
    private Set<Couplet> couplets;
    private Set<Word> words;

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Set<Couplet> getCouplets() {
        return couplets;
    }

    public void setCouplets(Set<Couplet> couplets) {
        this.couplets = couplets;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
