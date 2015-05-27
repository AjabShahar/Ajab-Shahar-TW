var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.SongFilterConfig = (function () {
    var filterCategories = [
        {
            name: "singersAsList[].name",
            value: "",
            displayName: "Singer"
        },
        {
            name: "poet",
            value: "",
            displayName: "Poet"
        },
        {
            name: "words[].transliteration",
            value: "",
            displayName: "Word"
        },
        {
            name: "gathering",
            value: "",
            displayName: "Gathering"
        },
        {
            name: "englishTransliteration",
            value: "",
            method: "startsWith"
        },
        {
            name: "englishTranslation",
            value: "",
            method: "startsWith"
        }
    ];

    var filterItemsLoader = {
        "Word": "getWordsFrom",
        "Singer": "getSingersFrom",
        "Poet": "getPoetsFrom",
        "Gathering": "getGatheringsFrom"
    };

    return {
        filterCategories: filterCategories,
        filterItemsLoader: filterItemsLoader
    };
})();