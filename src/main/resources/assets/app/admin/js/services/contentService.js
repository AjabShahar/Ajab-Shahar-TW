var contentService = function ($http) {
  var getAllCategories = function (type) {
    return $http.get('/api/category/'+type);
  };

  var getAllSongs = function () {
    return $http.get('/api/songs/getAllSongs');
  };

  var getAllPeople = function () {
    return $http.get('/api/people');
  };

  var getAllCouplets = function () {
    return $http.get('/api/couplets');
  };

  var getAllWords = function () {
    return $http.get('/api/words/getAllWords');
  };

  var getAllGenres = function () {
    return $http.get('/api/genres');
  };

  var getAllReflections = function(){
    return $http.get('/api/reflections/all');
  };

  var saveWord = function(word){
    return $http.post('/api/words', word);
  };

  var getWord = function(wordID){
    return $http.get('/api/words/edit?id=' + wordID);
  };

  var updateWord = function(word){
    return $http.post('/api/words/edit', word);
  };

  return {
    getAllPeople: getAllPeople,
    getAllCategories: getAllCategories,
    getAllSongs: getAllSongs,
    getAllCouplets: getAllCouplets,
    getAllWords: getAllWords,
    getAllGenres: getAllGenres,
    getAllReflections: getAllReflections,
    saveWord: saveWord,
    getWord: getWord,
    updateWord: updateWord
  };
};