'use strict';


angular.module("word").service("wordService",["$http",function ($http) {

    var getWords = function () {
        return $http.get('/api/words?publish=true');
    };

    var getGlossaryPageContent = function () {
        return $http.get('/api/words?publish=true');
    };

    var getWord = function(id){
        return $http.get('/api/words/edit?id='+id + "&publish=true");
    };

    var _moveDefaultReflectionToFront = function(reflections,defaultReflectionId){
        for(var i=0;i<reflections.length;i++){
            if(reflections[i].id === defaultReflectionId){
                var defaultReflection = reflections[i];
                reflections[i] = reflections[0];
                reflections[0] = defaultReflection;
                break;
            }
        }
    };
    var getReflectionsFrom = function(word){
        var reflections = [];
        var defaultReflectionId= null;
        if(!_.isEmpty(word.defaultReflection) && word.defaultReflection.published){
            defaultReflectionId = word.defaultReflection.id;
        }

        var relatedReflections = word.reflections?word.reflections.filter(function(reflection){ return reflection.published }):undefined;
        if(defaultReflectionId && !_.isEmpty(relatedReflections)){
            _moveDefaultReflectionToFront(relatedReflections,defaultReflectionId);
        }
        reflections.push(relatedReflections);
        return relatedReflections;
    };

    var getReflection = function(id){
        return $http.get('/api/reflections/edit?id='+id);
    };

    return {
        getWords: getWords,
        getGlossaryPageContent: getGlossaryPageContent,
        getWord:getWord,
        getReflectionsFrom:getReflectionsFrom,
        getReflection:getReflection
    };
}]);
