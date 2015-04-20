animationModule.service("sortService",function(){

    var sortList = function (words,contentTextRepresentation) {
        var sortFunction = contentTextRepresentation === 'Transliteration' ? compareTranslitTitles : compareEnglishTitles;
        return words.sort(sortFunction);
    };

    var compareEnglishTitles = function (firstWord, secondWord) {
        return firstWord.wordTranslation.localeCompare(secondWord.wordTranslation);
    };

    var compareTranslitTitles = function(firstWord, secondWord){
        return firstWord.wordTransliteration.localeCompare(secondWord.wordTransliteration);
    };

    return{
        sortList :sortList
    };
});