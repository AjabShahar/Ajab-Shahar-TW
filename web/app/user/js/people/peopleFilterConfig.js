var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.PeopleFilterConfig = (function () {
    var filterCategories = [
        {
            name: "name",
            value: "",
            method: "startsWith"
        },
        {
            name: "occupations[]",
            value: ""
        }
    ];

    return {
        filterCategories: filterCategories
    };
})();