package org.ajabshahar.api;


import java.util.ArrayList;
import java.util.List;

public class LyricsSummaryRepresentation {
  private List<UnitOfLyricsRepresentation> lyrics;

  public LyricsSummaryRepresentation(){
      lyrics=new ArrayList<>();
  }
  public void add(UnitOfLyricsRepresentation unitOfLyricsRepresentation) {
        lyrics.add(unitOfLyricsRepresentation);
  }
  public List<UnitOfLyricsRepresentation> getLyrics(){
      return lyrics;
  }

}
