var songsContentService = function ($http) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs/versions?songId='+id);
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getSongRenditions = function (id) {
        return $http.get('/api/songs');
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getAllSongs = function () {
        return $http.get('/api/songs/getPublishedSongs');
    };

    var getSongsInRangeAndFilteredBy = function(startIndex, letter){
        return $http.get('/api/songs/getPublishedSongs?startFrom=' + startIndex + "&filteredLetter=" + letter);
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