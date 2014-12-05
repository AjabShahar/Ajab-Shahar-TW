var contentService = function ($http) {
  var getAllSongs = function () {
    return $http.get('/api/songs/getsongs');
  };

  var getAllCouplets = function () {
    return $http.get('/api/couplets');
  };

  var getAllWords = function () {
    return $http.get('/api/couplets');
  };

  return {
    getAllSongs: getAllSongs,
    getAllCouplets:getAllCouplets,
    getAllWords:getAllWords,
  };
};