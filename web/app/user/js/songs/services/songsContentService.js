var songsContentService = function ($http) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs/versions?songId='+id);
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getSongRenditions = function (id) {
        return $http.get('/api/songs/versions?songId='+id);
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getAllSongs = function () {
        return $http.get('/api/songs/getPublishedSongs');
    };

    var getSongsInRangeAndFilteredBy = function(startIndex, letter){
        return $http.get('/api/songs/getPublishedSongs?startFrom=' + startIndex + "&filteredLetter=" + letter);
    };

    var getSongsStartingWith = function(letter){
        return $http.get('/api/songs/count/startingWith?letter=' + letter);
    };

    var getSong = function (id) {
        return $http.get('/api/songs/getPublishedSongs/'+id);
    };

    //var wordSeen = function(word,wordsList){
    //    return _.some(wordsList,function(wordInList){
    //        return wordInList[0] === word.translation;
    //    })
    //};

    var getWordsIn = function(songs){
        var wordsDictionary = {};
        if(!_.isEmpty(songs)){
            _.reduce(songs,function(wordsDictionary,song){
                //console.log(song.words);
                if(!_.isEmpty(song.words)){
                    song.words.forEach(function(word){
                        wordsDictionary[word.id] =[word.transliteration,word.translation];
                    })
                }
                return wordsDictionary;
            },wordsDictionary )
        }
        return _.values(wordsDictionary);
    };

    var getGatheringsIn = function(){

    };


    return {
        getAllSongs: getAllSongs,
        getSongsVersions:getSongsVersions,
        getSongRenditions:getSongRenditions,
        getSongsInRangeAndFilteredBy:getSongsInRangeAndFilteredBy,
        getSongsStartingWith:getSongsStartingWith,
        getSong: getSong,
        getWordsIn:getWordsIn
    };
};
