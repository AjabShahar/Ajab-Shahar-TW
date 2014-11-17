var songsContentService = function ($http) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs');
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getSongRenditions = function (id) {
        return $http.get('/api/songs');
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getAllSongs = function () {
        return $http.get('/api/songs');
    };

    var getSongsInRangeAndFilteredBy = function(startIndex, letter){
        console.log("Request made: " + '/api/songs/filterByWithRange?startingIndex=' + startIndex + "&letter=" + letter);
        return $http.get('/api/songs/filterByWithRange?startingIndex=' + startIndex + "&letter=" + letter);
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