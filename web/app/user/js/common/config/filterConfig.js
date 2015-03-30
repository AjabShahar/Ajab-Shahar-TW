var Ajabshahar = Ajabshahar || {};
Ajabshahar.user = Ajabshahar.user || {};
Ajabshahar.user.SongFilterConfig = (function(){
    var filterCategories = [
        {
            name:"words[].transliteration",
            value:"",
            displayName:"Words"
        },
        {
            name:"englishTransliteration",
            value:""
        },
        {
            name:"englishTranslation",
            value:""
        }
    ];

    var filterItemsLoader ={
        "Words":"getWordsIn"
    };
    return {
        filterCategories:filterCategories,
        filterItemsLoader:filterItemsLoader
    };
})();