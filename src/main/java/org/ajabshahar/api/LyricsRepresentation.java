package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LyricsRepresentation {
    private LyricsSummaryRepresentation originalLyrics;
    private LyricsSummaryRepresentation englishTranslationLyrics;
    private LyricsSummaryRepresentation englishTransliterationLyrics;
    private String chorus;


    public LyricsRepresentation(LyricsSummaryRepresentation originalLyrics, LyricsSummaryRepresentation englishTranslationLyrics, LyricsSummaryRepresentation englishTransliterationLyrics, String chorus) {
        this.originalLyrics = originalLyrics;
        this.englishTranslationLyrics = englishTranslationLyrics;
        this.englishTransliterationLyrics = englishTransliterationLyrics;
        this.chorus = chorus;
    }

    public LyricsRepresentation() {

    }

    @JsonProperty("originalLyrics")
    public LyricsSummaryRepresentation getOriginalLyrics() {
        return originalLyrics;
    }

    @JsonProperty("englishTranslationLyrics")
    public LyricsSummaryRepresentation getEnglishTranslationLyrics() {
        return englishTranslationLyrics;
    }

    @JsonProperty("englishTransliterationLyrics")
    public LyricsSummaryRepresentation getEnglishTransliterationLyrics() {
        return englishTransliterationLyrics;
    }

    @JsonProperty("chorus")
    public String getChorus() {
        return chorus;
    }
}
