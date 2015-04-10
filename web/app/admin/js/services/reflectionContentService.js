var reflectionContentService = function ($http) {

    var getPeople = function () {
        return $http.get('/api/people/summary');
    };

    var getRefectionById = function (id) {
        return $http.get('/api/reflections/edit?id=' + id);
    };

    var saveReflection = function (data) {
        return $http.post('/api/reflections', data);
    };

    var getWords = function(){
       return $http.get('/api/words/summary');
    };

    return {
        getPeople: getPeople,
        getRefectionById: getRefectionById,
        saveReflection: saveReflection,
        getWords: getWords
    };

};
