angular.module("word").service("wordService",["$http",function ($http) {

    var getVersions = function (wordId) {
        return $http.get('/api/words/reflections?id=' + wordId);
    };

    var getWordsLandingPageContent = function () {
        return $http.get('/api/words?showOnWordsLandingPage=true');
    };

    var getGlossaryPageContent = function () {
        return $http.get('/api/words');
    };

    return {
        getVersions: getVersions,
        getWordsLandingPageContent: getWordsLandingPageContent,
        getGlossaryPageContent: getGlossaryPageContent
    };
}]);
