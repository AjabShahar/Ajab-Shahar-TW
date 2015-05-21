var songsContentService = function ($http) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs/versions?id=' + id);
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getSongRenditions = function (id) {
        return $http.get('/api/songs/versions?songId=' + id);
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getAllSongs = function () {
        return $http.get('/api/songs/getPublishedSongs');
    };

    var getSong = function (id) {
        return $http.get('/api/songs/getPublishedSongs/' + id);
    };

    //var wordSeen = function(word,wordsList){
    //    return _.some(wordsList,function(wordInList){
    //        return wordInList[0] === word.translation;
    //    })
    //};

    var getWordsFrom = function (songs) {
        var wordsDictionary = {};
        if (!_.isEmpty(songs)) {
            _.reduce(songs, function (wordsDictionary, song) {
                //console.log(song.words);
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


    return {
        getAllSongs: getAllSongs,
        getSongsVersions: getSongsVersions,
        getSongRenditions: getSongRenditions,
        getSong: getSong,
        getWordsFrom: getWordsFrom,
        getSingersFrom: getSingersFrom,
        getPoetsFrom: getPoetsFrom,
        getGatheringsFrom: getGatheringsFrom
    };
};
