var genreContentService = function ($http) {

  var saveGenre = function(genre){
    return $http.post('/api/genres', genre)
  }

  var getGenre = function (genreId) {
    return $http.get('/api/genres/' + genreId);
  };

  return {
    saveGenre:saveGenre,
    getGenre: getGenre
  };
};