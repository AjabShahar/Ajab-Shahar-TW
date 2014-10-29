var contentService = function ($http) {
  var getAllSongs = function () {
    return $http.get('/api/songs');
  };

  return {
    getAllSongs: getAllSongs,
  };
};