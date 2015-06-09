var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.ReflectionFilterConfig = (function () {
    var filterCategories = [
        {
            name: "words[].wordTransliteration",
            value: "",
            displayName: "Word"
        },
        {
            name: "speaker.name",
            value: "",
            displayName: "Speaker"
        }
    ];

    var filterItemsLoader = {
        "Word": "getWordsFrom",
        "Speaker": "getSpeakersFrom"
    };

    return {
        filterCategories: filterCategories,
        filterItemsLoader: filterItemsLoader
    };
})();