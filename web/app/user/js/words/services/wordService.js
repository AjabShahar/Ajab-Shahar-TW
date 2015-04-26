'use strict';

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

    var getWord = function(id){
        return $http.get('/api/words/edit?id='+id);
    };

    var getReflectionsFrom = function(word){
        var reflections = [];
        if(!_.isEmpty(word.defaultReflection)){
            reflections.push(word.defaultReflection)
        }
        return reflections.concat(word.reflections);
    };

    var getReflection = function(id){
        return $http.get('/api/reflections/edit?id='+id);
    };

    return {
        getVersions: getVersions,
        getWordsLandingPageContent: getWordsLandingPageContent,
        getGlossaryPageContent: getGlossaryPageContent,
        getWord:getWord,
        getReflectionsFrom:getReflectionsFrom,
        getReflection:getReflection
    };
}]);
