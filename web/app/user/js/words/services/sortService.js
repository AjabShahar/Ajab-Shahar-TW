animationModule.service("sortService",function(){

    var sortList = function (list,contentTextRepresentation) {
        var sortFunction = contentTextRepresentation === 'Transliteration' ? compareTranslitTitles : compareEnglishTitles;
        return list.sort(sortFunction);
    };

    var compareEnglishTitles = function (firstItem, secondItem) {
        if(firstItem.isSong)
           return firstItem.englishTranslation.localeCompare(secondItem.englishTranslation);
        return firstItem.wordTranslation.localeCompare(secondItem.wordTranslation);
    };

    var compareTranslitTitles = function(firstItem, secondItem){
        if(firstItem.isSong)
           return firstItem.englishTransliteration.localeCompare(secondItem.englishTransliteration);
        return firstItem.wordTransliteration.localeCompare(secondItem.wordTransliteration);
    };

    return{
        sortList :sortList
    };
});