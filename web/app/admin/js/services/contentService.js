var contentService = function ($http) {
    var getAllCategories = function (type) {
        return $http.get('/api/category/' + type);
    };

    var getAllSongs = function () {
        return $http.get('/api/songs');
    };

    var getAllPeople = function () {
        return $http.get('/api/people');
    };

    var getAllPeopleSummary = function(params){
        return $http.get('/api/people/summary',{params:params});
    };

    var getPoets = function () {
        return $http.get('/api/people?role=Poet');
    };

    var getAllPoetsSummary = function () {
        return getAllPeopleSummary({role:'Poet'})
    };

    var getAllCouplets = function () {
        return $http.get('/api/couplets');
    };

    var getAllWordsSummaries = function () {
        return $http.get('/api/words/summary');
    };

    var getAllGenres = function () {
        return $http.get('/api/genres');
    };

    var getAllReflectionSummaries = function () {
        return $http.get('/api/reflections');
    };

    var saveWord = function (word) {
        return $http.post('/api/words', word);
    };

    var getWord = function (wordID) {
        return $http.get('/api/words/edit?id=' + wordID);
    };

    var updateWord = function (word) {
        return $http.post('/api/words/edit', word);
    };

    return {
        getAllPeople: getAllPeople,
        getAllPeopleSummary: getAllPeopleSummary,
        getAllPoetsSummary: getAllPoetsSummary,
        getAllCategories: getAllCategories,
        getAllSongs: getAllSongs,
        getAllCouplets: getAllCouplets,
        getAllWordsSummaries: getAllWordsSummaries,
        getAllGenres: getAllGenres,
        getAllReflectionSummaries: getAllReflectionSummaries,
        saveWord: saveWord,
        getWord: getWord,
        updateWord: updateWord,
        getPoets: getPoets
    };
};
