angular.module("reflection").service("reflectionsContentService",["$http",function($http) {
    var getAllReflections = function () {
        return $http.get('/api/reflections/completeInfo?content=featured');
    };

    var getReflection = function (id) {
        return $http.get('/api/reflections/edit?id=' + id);
    };

    var getPublishedReflections = function(){
       return $http.get('/api/reflections/completeInfo?content=authoringComplete');
    };

    var getWordsFrom = function (reflections) {
        var wordsDictionary = {};
        if (!_.isEmpty(reflections)) {
            _.reduce(reflections, function (wordsDictionary, reflection) {
                if (!_.isEmpty(reflection.words)) {
                    reflection.words.forEach(function (word) {
                        wordsDictionary[word.id] = [word.wordTransliteration, word.wordTranslation];
                    })
                }
                return wordsDictionary;
            }, wordsDictionary)
        }
        return _.values(wordsDictionary);
    };

    var getPeopleFrom = function (reflections) {
        var peopleList = {};
        if (!_.isEmpty(reflections)) {
            _.reduce(reflections, function (peopleList, reflection) {
                if (!_.isEmpty(reflection.people)) {
                    reflection.people.forEach(function (person) {
                        peopleList[person.id] = [person.name];
                    })
                }
                return peopleList;
            }, peopleList)
        }
        return _.values(peopleList);
    };

    var _pluckFields = function (reflections, field) {
        var valuesMap = {};
        if (!_.isEmpty(reflections)) {
            _.reduce(reflections, function (valuesMap, reflection) {
                if (!_.isEmpty(reflection[field])) {
                    valuesMap[reflection[field].name] = [reflection[field].name];
                }
                return valuesMap;
            }, valuesMap)
        }
        return _.values(valuesMap);
    };

    var getSpeakersFrom = function (reflections) {
        return _pluckFields(reflections, "speaker");
    };

    return {
        getAllReflections: getAllReflections,
        getReflection: getReflection,
        getPublishedReflections: getPublishedReflections,
        getWordsFrom: getWordsFrom,
        getSpeakersFrom: getSpeakersFrom,
        getPeopleFrom: getPeopleFrom
    };
}]);
