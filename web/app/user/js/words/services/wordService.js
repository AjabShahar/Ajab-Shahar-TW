var wordService = function ($http) {

    var getVersions = function (wordId) {
        return $http.get('/api/words/reflections?id=' + wordId);
    };

    return {
        getVersions: getVersions
    };
};
