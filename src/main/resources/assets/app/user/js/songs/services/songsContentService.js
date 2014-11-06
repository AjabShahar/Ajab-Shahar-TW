var songsContentService = function ($http) {
    var getSongsVersions = function (id) {
        return $http.get('/api/songs');
        //return $http.get('/api/songs/'+id+"/versions");
    };

    var getAllSongs = function () {
        return $http.get('/api/songs');
    };


    var getSongsGivenRange = function(from, to){
        return $http.get('/api/songs/range?from=' + from + "&to=" + to);
    };

    return {
        getAllSongs: getAllSongs,
        getSongsGivenRange: getSongsGivenRange,
        getSongsVersions:getSongsVersions,
    };
};