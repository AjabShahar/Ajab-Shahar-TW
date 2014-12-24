package org.ajabshahar.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LyricsRepresentation {
    private LyricsSummaryRepresentation originalLyrics;
    private LyricsSummaryRepresentation englishTranslationLyrics;
    private LyricsSummaryRepresentation englishTransliterationLyrics;


    public LyricsRepresentation(LyricsSummaryRepresentation originalLyrics, LyricsSummaryRepresentation englishTranslationLyrics, LyricsSummaryRepresentation englishTransliterationLyrics) {
        this.originalLyrics = originalLyrics;
        this.englishTranslationLyrics = englishTranslationLyrics;
        this.englishTransliterationLyrics = englishTransliterationLyrics;
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
}
