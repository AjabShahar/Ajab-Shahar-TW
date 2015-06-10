var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.ReflectionFilterConfig = (function () {
    var filterCategories = [
        {
            name: "words[].wordTransliteration",
            value: "",
            displayName: "Words",
            tooltip: "Words, metaphors or ideas in or about mystic poetry"
        },
        {
            name: "speaker.name",
            value: "",
            displayName: "Speakers",
            tooltip: "Speakers or writers who have reflected on a song, poem or idea"
        },
        {
            name: "people[].name",
            value: "",
            displayName: "People",
            tooltip: "Poets or other people, who are the subject of a reflection"
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