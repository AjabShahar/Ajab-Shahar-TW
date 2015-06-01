var reflectionContentService = function ($http) {

    var getPeople = function () {
        return $http.get('/api/people/summary?show=all');
    };

    var getRefectionById = function (id) {
        return $http.get('/api/reflections/edit?id=' + id + "&publish=false");
    };

    var saveReflection = function (data) {
        return $http.post('/api/reflections', data);
    };

    var getWords = function(){
       return $http.get('/api/words/summary');
    };

    var getSongs = function(){
        return $http.get('/api/songs');
    };

    return {
        getPeople: getPeople,
        getRefectionById: getRefectionById,
        saveReflection: saveReflection,
        getWords: getWords,
        getSongs: getSongs
    };

};
