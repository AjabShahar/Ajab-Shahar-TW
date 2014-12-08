var songsContentService = function ($http) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs/getsongs');
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getSongRenditions = function (id) {
        return $http.get('/api/songs');
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getAllSongs = function () {
        return $http.get('/api/songs/getsongs');
    };

    var getSongsInRangeAndFilteredBy = function(startIndex, letter){
        return $http.get('/api/songs/getsongs?startFrom=' + startIndex + "&filteredLetter=" + letter);
    }

    var getSongsStartingWith = function(letter){
        return $http.get('/api/songs/count/startingWith?letter=' + letter);
    }

    return {
        getAllSongs: getAllSongs,
        getSongsVersions:getSongsVersions,
        getSongRenditions:getSongRenditions,
        getSongsInRangeAndFilteredBy:getSongsInRangeAndFilteredBy,
        getSongsStartingWith:getSongsStartingWith
    };
};