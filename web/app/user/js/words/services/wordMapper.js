angular.module("word").factory('wordMapper', [function () {
    getThumbnails = function (words, customStyle) {
        return _.reduce(words, function (thumbnails, word) {
            thumbnails.push({
                "id": word.id,
                "isWord": true,
                "contentId": 'word_' + word.id,
                "wordOriginal": word.wordOriginal,
                "wordTranslation": word.wordTranslation,
                "wordTransliteration": word.wordTransliteration,
                "hindiIntroExcerpt": word.hindiIntroExcerpt,
                "englishIntroExcerpt": word.englishIntroExcerpt,
                "hasWordIntro":!_.isEmpty(word.wordIntroEnglish),
                "customStyle": (customStyle) ? customStyle() : ''
            });
            return thumbnails;
        }, []);
    };

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

    getWordDetails = function (words) {
        return _.reduce(words, function (details, word) {
            details.push({
                "wordOriginal": word.wordOriginal,
                "wordTranslation": word.wordTranslation,
                "wordTransliteration": word.wordTransliteration,
                "hindiIntroExcerpt": word.hindiIntroExcerpt,
                "englishIntroExcerpt": word.englishIntroExcerpt
            });
            return details;
        }, []);
    };
    getWordCompleteInfo = function (words) {
        return _.reduce(words, function (details, word) {
            details.push({
                "wordOriginal": word.wordOriginal,
                "wordTranslation": word.wordTranslation,
                "wordTransliteration": word.wordTransliteration,
                "hindiIntroExcerpt": word.hindiIntroExcerpt,
                "englishIntroExcerpt": word.englishIntroExcerpt,
                "wordIntroHindi": word.wordIntroHindi,
                "wordIntroEnglish": word.wordIntroEnglish
            });
            return details;
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
        getThumbnails: getThumbnails,
        getWordDetails: getWordDetails,
        getOverviews: getOverviews,
        getWordCompleteInfo: getWordCompleteInfo,
        getBasicDetails: getBasicDetails
    };
}]);
