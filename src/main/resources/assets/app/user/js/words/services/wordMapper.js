var wordMapper = function () {
    getThumbnails = function(words,customStyle) {
        return _.reduce(words,function(thumbnails, word,index) {
            thumbnails.push({
                "id":word.id,
                "isWord":true,
                "contentId":'word_'+word.id,
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "hindiIntroExcerpt":word.hindiIntroExcerpt,
                "englishIntroExcerpt":word.englishIntroExcerpt,
                "customStyle": (customStyle)? customStyle():'',
            });
            return thumbnails;
        },[]);
    };

    getOverviews = function(words,customStyle) {
        return _.reduce(words,function(introductions, word,index) {
            introductions.push({
                "id":word.id,
                "isWord":true,
                "contentId":'word_'+word.id,
                "wordOriginal":word.wordOriginal,
                "wordTranslation":word.wordTranslation,
                "wordTransliteration":word.wordTransliteration,
                "hindiIntroExcerpt":word.hindiIntroExcerpt,
                "englishIntroExcerpt":word.englishIntroExcerpt,
                "wordIntroEnglish":word.wordIntroEnglish,
                "writers":word.writers,
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
                "hindiIntroExcerpt":word.hindiIntroExcerpt,
                "englishIntroExcerpt":word.englishIntroExcerpt,
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
                "hindiIntroExcerpt":word.hindiIntroExcerpt,
                "englishIntroExcerpt":word.englishIntroExcerpt,
                "wordIntroHindi":word.wordIntroHindi,
                "wordIntroEnglish":word.wordIntroEnglish,
            });
            return details;
       },[]);
    }

    getBasicDetails = function(words){
       return _.reduce(words,function(wordBasicInfo,word,index){
            wordBasicInfo.push({
               "id" : word.id,
               "transliteration":word.wordTransliteration,
               "isRootWord":word.rootWord,

            });
            return wordBasicInfo;
       },[]);
    }

    return {
        getThumbnails: getThumbnails,
        getWordDetails: getWordDetails,
        getOverviews:getOverviews,
        getWordCompleteInfo:getWordCompleteInfo,
        getBasicDetails:getBasicDetails
    };
};

thumbnailModule.factory('wordMapper', [wordMapper]);