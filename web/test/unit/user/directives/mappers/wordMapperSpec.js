"use strict";

describe("Should test word mapper", function () {
    var wordMapper;
    var wordList;
    beforeEach(function () {
        module("thumbnailModule");
        wordList = [{
            "id": 1,
            "wordOriginal": "Original word",
            "wordTranslation": "Translated word",
            "wordTransliteration": "Transliterated word",
            "hindiIntroExcerpt": "Hindi Word Intro",
            "englishIntroExcerpt": "English Word Intro",
            "wordIntroEnglish": "Word Intro English",
            "wordIntroHindi": "Word Intro Hindi",
            "writers": [],
            "rootWord": true,
            "displayAjabShaharTeam": true,
            "publish":true
        }]
    });

    beforeEach(inject(function (_wordMapper_) {
        wordMapper = _wordMapper_;
    }));

    it("Should get overview representation", function () {
        var words = wordMapper.getOverviews(wordList);

        expect(words.length).toBe(1);
        expect(words[0].id).toBe(1);
        expect(words[0].wordOriginal).toBe("Original word");
        expect(words[0].wordTranslation).toBe("Translated word");
        expect(words[0].wordTransliteration).toBe("Transliterated word");
        expect(words[0].hindiIntroExcerpt).toBe("Hindi Word Intro");
        expect(words[0].englishIntroExcerpt).toBe("English Word Intro");
        expect(words[0].wordIntroEnglish).toBe("Word Intro English");
        expect(words[0].writers.length).toBe(0);
        expect(words[0].displayAjabShaharTeam).toBe(true);
    });


    it("Should get Basic representation", function () {
        var words = wordMapper.getBasicDetails(wordList);

        expect(words.length).toBe(1);
        expect(words[0].id).toBe(1);
        expect(words[0].transliteration).toBe("Transliterated word");
        expect(words[0].isRootWord).toBe(true);

    });
});
