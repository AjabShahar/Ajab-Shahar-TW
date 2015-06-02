"use strict";

var songsContentService = function ($http,$q) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs/versions?id=' + id);
    };

    var getSongRenditions = function (id) {
        return $http.get('/api/songs/versions?songId=' + id);
    };

    var getAllSongs = function () {
        return $http.get('/api/songs/getPublishedSongs');
    };

    var getSong = function (id) {
        return $http.get('/api/songs/getPublishedSongs/' + id);
    };
    var getWordsFrom = function (songs) {
        var wordsDictionary = {};
        if (!_.isEmpty(songs)) {
            _.reduce(songs, function (wordsDictionary, song) {
                if (!_.isEmpty(song.words)) {
                    song.words.forEach(function (word) {
                        wordsDictionary[word.id] = [word.transliteration, word.translation];
                    })
                }
                return wordsDictionary;
            }, wordsDictionary)
        }
        return _.values(wordsDictionary);
    };

    var getSingersFrom = function (songs) {
        var singersMap = {};

        if (!_.isEmpty(songs)) {
            _.reduce(songs, function (singersMap, song) {
                if (!_.isEmpty(song.singersAsList)) {
                    song.singersAsList.forEach(function (singer) {
                        singersMap[singer.id] = [singer.name];
                    })
                }
                return singersMap;
            }, singersMap)
        }
        return _.values(singersMap);
    };

    var _pluckFields = function (songs, field) {
        var valuesMap = {};
        if (!_.isEmpty(songs)) {
            _.reduce(songs, function (valuesMap, song) {
                if (!_.isEmpty(song[field])) {
                    valuesMap[song[field]] = [song[field]];
                }
                return valuesMap;
            }, valuesMap)
        }
        return _.values(valuesMap);
    };
    var getPoetsFrom = function (songs) {
        return _pluckFields(songs, "poet");
    };

    var getGatheringsFrom = function (songs) {
        return _pluckFields(songs, "gathering");
    };

    var getRelatedContentFromRelatedWordsOf = function(song){
        if(!_.isEmpty(song.words)){
            var relatedWordIds = song.words.filter(function(word){
                return word.publish;
            }).map(function(word){
                return word.id;
            });
            if(!_.isEmpty(relatedWordIds)){
                return $http.get('/api/words/',{
                    params:{
                        ids:relatedWordIds,
                        representation:"custom"
                    }
                });
            }
        }
        return $q.reject({});
    };

    return {
        getAllSongs: getAllSongs,
        getSongsVersions: getSongsVersions,
        getSongRenditions: getSongRenditions,
        getSong: getSong,
        getWordsFrom: getWordsFrom,
        getSingersFrom: getSingersFrom,
        getPoetsFrom: getPoetsFrom,
        getGatheringsFrom: getGatheringsFrom,
        getRelatedContentFromRelatedWordsOf:getRelatedContentFromRelatedWordsOf
    };
};
