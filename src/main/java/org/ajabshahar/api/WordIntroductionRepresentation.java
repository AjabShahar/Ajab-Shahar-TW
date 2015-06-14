package org.ajabshahar.api;

import lombok.Getter;
import lombok.Setter;
import org.ajabshahar.platform.models.WordIntroduction;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
public class WordIntroductionRepresentation {
    private long id;
    private String wordIntroHindi;
    private String wordIntroEnglish;
    private String contentType;
    private PersonSummaryRepresentation poet;


    public static WordIntroductionRepresentation createFrom(WordIntroduction wordIntroduction){
        WordIntroductionRepresentation wordIntroductionRepresentation = new WordIntroductionRepresentation();
        wordIntroductionRepresentation.id = wordIntroduction.getId();
        wordIntroductionRepresentation.wordIntroHindi = wordIntroduction.getWordIntroHindi();
        wordIntroductionRepresentation.wordIntroEnglish = wordIntroduction.getWordIntroEnglish();
        wordIntroductionRepresentation.contentType = wordIntroduction.getContentType();
//        wordIntroductionRepresentation.poet = PersonSummaryRepresentation.createFrom(wordIntroduction.getPoet());
        return wordIntroductionRepresentation;
    }

    public WordIntroduction toWordIntroduction(){
        WordIntroduction wordIntroduction = new WordIntroduction();

        wordIntroduction.setId(id);
        wordIntroduction.setWordIntroHindi(wordIntroHindi);
        wordIntroduction.setWordIntroEnglish(wordIntroEnglish);
        wordIntroduction.setContentType(contentType);
        wordIntroduction.setPoet(PersonRepresentationFactory.toPerson(poet));

        return wordIntroduction;
    }

    public Set<WordIntroductionRepresentation> toWordIntroductionRepresentations(Set<WordIntroduction> wordIntroductions) {
        Set<WordIntroductionRepresentation> wordIntroductionRepresentations = new LinkedHashSet<>();
        if(wordIntroductions != null){
            for (WordIntroduction wordIntroduction : wordIntroductions) {
                wordIntroductionRepresentations.add(createFrom(wordIntroduction));
            }
        }
        return wordIntroductionRepresentations;
    }
}
