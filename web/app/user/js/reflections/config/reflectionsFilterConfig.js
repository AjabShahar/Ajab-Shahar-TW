var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.ReflectionFilterConfig = (function () {
    var filterCategories = [
        {
            name: "words[].wordTransliteration",
            value: "",
            displayName: "Words"
        },
        {
            name: "speaker.name",
            value: "",
            displayName: "Speakers"
        },
        {
            name: "people[].name",
            value: "",
            displayName: "People"
        }
    ];

    var filterItemsLoader = {
        "Words": "getWordsFrom",
        "Speakers": "getSpeakersFrom",
        "People": "getPeopleFrom"
    };

    return {
        filterCategories: filterCategories,
        filterItemsLoader: filterItemsLoader
    };
})();