var songsContentService = function ($http) {
  var getAllSongs = function () {
    return $http.get('/api/songs');
  };

  var getSongsGivenRange = function(from, to){
  	return $http.get('/api/songs/range?from=' + from + "&to=" + to);
  };

  return {
    getAllSongs: getAllSongs,
    getSongsGivenRange: getSongsGivenRange
  };
};