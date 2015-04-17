package org.ajabshahar.factory;

import org.ajabshahar.api.WordIntermediateRepresentation;
import org.ajabshahar.api.WordRepresentationFactory;
import org.ajabshahar.api.WordSummaryRepresentation;
import org.ajabshahar.platform.models.Word;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class WordRepresentationFactoryTest {

    @Test
    public void shouldConvertSummaryRepresentationsToWords(){
        int wordID = 1;
        ArrayList<WordSummaryRepresentation> relatedWords = new ArrayList<>();
        WordSummaryRepresentation wordSummaryRepresentation = new WordSummaryRepresentation();
        wordSummaryRepresentation.setId(wordID);
        relatedWords.add(wordSummaryRepresentation);

        Set<Word> words = WordRepresentationFactory.toWords(relatedWords);

        assertThat(words.iterator().next().getId(), IsEqual.equalTo((long)wordID));
    };
}
