'use strict';

angular.module("word").service("wordService",["$http",function ($http) {

    var getVersions = function (wordId) {
        return $http.get('/api/words/reflections?id=' + wordId);
    };

    var getWords = function () {
        return $http.get('/api/words?publish=true');
    };

    var getGlossaryPageContent = function () {
        return $http.get('/api/words?publish=true');
    };

    var getWord = function(id){
        return $http.get('/api/words/edit?id='+id);
    };

    var getReflectionsFrom = function(word){
        var reflections = [];
        if(!_.isEmpty(word.defaultReflection)){
            reflections.push(word.defaultReflection)
        }
        var relatedReflections = word.reflections?word.reflections.filter(function(reflection){ return reflection.published }):undefined;
        return reflections.concat(relatedReflections);
    };

    var getReflection = function(id){
        return $http.get('/api/reflections/edit?id='+id);
    };

    return {
        getVersions: getVersions,
        getWords: getWords,
        getGlossaryPageContent: getGlossaryPageContent,
        getWord:getWord,
        getReflectionsFrom:getReflectionsFrom,
        getReflection:getReflection
    };
}]);
