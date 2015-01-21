var wordMapper = function () {
    getThumbnails = function(words,customStyle) {
        return _.reduce(words,function(thumbnails, word,index) {
            thumbnails.push({
                "id":word.id,
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "introSummaryOriginal":word.introSummaryOriginal,
                "introSummaryTranslation":word.introSummaryTranslation,
                "introSummaryTransliteration":word.introSummaryTransliteration,
                "introTransliteration":word.wordIntroTransliteration,
                "customStyle": (customStyle)? customStyle():'',
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
    getWordCompleteInfo = function(words){
       return _.reduce(words,function(details, word,index) {
            details.push({
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "introSummaryOriginal":word.introSummaryOriginal,
                "introSummaryTranslation":word.introSummaryTranslation,
                "introSummaryTransliteration":word.introSummaryTransliteration,
                "wordIntroOriginal":word.wordIntroOriginal,
                "wordIntroTranslation":word.wordIntroTranslation,
                "wordIntroTransliteration":word.wordIntroTransliteration,
            });
            return details;
       },[]);
    }

    return {
        getThumbnails: getThumbnails,
        getWordDetails: getWordDetails,
        getIntroductions:getIntroductions,
        getWordCompleteInfo:getWordCompleteInfo
    };
};

thumbnailModule.factory('wordMapper', [wordMapper]);