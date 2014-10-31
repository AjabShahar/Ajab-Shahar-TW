var contentService = function ($http) {
  var getAllSongs = function () {
    return $http.get('/api/songs');
  };

  var getAllCouplets = function () {
    return $http.get('/api/couplets');
  };

  return {
    getAllSongs: getAllSongs,
  };
};