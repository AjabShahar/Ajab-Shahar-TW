var reflectionsContentService = function ($http) {
    var getAllReflections = function () {
        return $http.get('/api/reflections/getPublishedReflections');
    };

    var getReflectionsInRangeAndFilteredBy = function(startIndex, letter){
        return $http.get('/api/reflections/getPublishedReflections?startFrom=' + startIndex + "&filteredLetter=" + letter);
    };

    var getReflectionsStartingWith = function(letter){
        return $http.get('/api/reflections/count/startingWith?letter=' + letter);
    };

    var getReflection = function (id) {
        return $http.get('/api/reflections/getPublishedReflections/'+id);
    };

    return {
        getAllReflections: getAllReflections,
        getReflectionsInRangeAndFilteredBy:getReflectionsInRangeAndFilteredBy,
        getReflectionsStartingWith:getReflectionsStartingWith,
        getReflection: getReflection
    };
};
