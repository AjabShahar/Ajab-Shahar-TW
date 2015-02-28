var genreContentService = function ($http) {

  var saveGenre = function(genre){
    return $http.post('/api/genres', genre);
  };

  var getGenre = function (genreId) {
    return $http.get('/api/genres/' + genreId);
  };

  var getAllGenres = function () {
    return $http.get('/api/genres');
  };

  return {
    saveGenre:saveGenre,
    getGenre: getGenre,
    getAllGenres: getAllGenres
  };
};