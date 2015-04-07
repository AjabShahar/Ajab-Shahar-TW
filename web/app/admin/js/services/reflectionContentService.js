var reflectionContentService = function ($http) {

    var getPeople = function () {
        return $http.get('/api/people');
    };

    var getRefectionById = function (id) {
        return $http.get('/api/reflections/edit?id=' + id);
    };

    var saveReflection = function (data) {
        return $http.post('/api/reflections', data);
    };

    return {
        getPeople: getPeople,
        getRefectionById: getRefectionById,
        saveReflection: saveReflection
    };

};
