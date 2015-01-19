var wordMapper = function () {
    getThumbnails = function(words,customStyle) {
        return _.reduce(words,function(thumbnails, word,index) {
            thumbnails.push({
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "introSummaryOriginal":word.introSummaryOriginal,
                "introSummaryTranslation":word.introSummaryTranslation,
                "introSummaryTransliteration":word.introSummaryTransliteration,
            });
            return thumbnails;
        },[]);
    };

    getIntroductions = function(words,customStyle) {
        return _.reduce(words,function(introductions, word,index) {
            introductions.push({
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "introSummaryOriginal":word.introSummaryOriginal,
                "introSummaryTranslation":word.introSummaryTranslation,
                "introSummaryTransliteration":word.introSummaryTransliteration,
            });
            return introductions;
        },[]);
    };

    getWordDetails = function(words){
        return _.reduce(words,function(details, word,index) {
            details.push({
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "introSummaryOriginal":word.introSummaryOriginal,
                "introSummaryTranslation":word.introSummaryTranslation,
                "introSummaryTransliteration":word.introSummaryTransliteration,
            });
            return details;
        },[]);
    }

    return {
        getThumbnails: getThumbnails,
        getWordDetails: getWordDetails,
        getIntroductions:getIntroductions
    };
};

thumbnailModule.factory('wordMapper', [wordMapper]);