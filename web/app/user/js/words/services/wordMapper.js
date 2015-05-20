angular.module("word").factory('wordMapper', [function () {

    getOverviews = function (words) {
        return _.reduce(words, function (introductions, word) {
            introductions.push({
                "id": word.id,
                "isWord": true,
                "contentId": 'word_' + word.id,
                "wordOriginal": word.wordOriginal,
                "wordTranslation": word.wordTranslation,
                "wordTransliteration": word.wordTransliteration,
                "hindiIntroExcerpt": word.hindiIntroExcerpt,
                "englishIntroExcerpt": word.englishIntroExcerpt,
                "wordIntroEnglish": word.wordIntroEnglish,
                "writers": word.writers,
                "displayAjabShaharTeam": word.displayAjabShaharTeam
            });
            return introductions;
        }, []);
    };


    getBasicDetails = function (words) {
        return _.reduce(words, function (wordBasicInfo, word) {
                wordBasicInfo.push({
                    "id": word.id,
                    "translation": word.wordTranslation,
                    "transliteration": word.wordTransliteration,
                    "isRootWord": word.rootWord

                });
            wordBasicInfo = _.sortBy(wordBasicInfo, 'transliteration');
            return wordBasicInfo;
        }, []);
    };

    return {
        getOverviews: getOverviews,
        getBasicDetails: getBasicDetails
    };
}]);
