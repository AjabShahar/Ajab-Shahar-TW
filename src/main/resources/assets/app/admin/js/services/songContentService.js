var songContentService = function ($http) {
    var getAllUmbrellaTitles = function () {
        return $http.get('/api/title/umbrella');
    }

    var getAllSongTitles = function () {
        return $http.get('/api/title/song');
    }

    var getAllSingers = function () {
        return $http.get('/api/people?role=Singer');
    }

    var getAllPoets = function () {
        return $http.get('/api/people?role=Poet');
    }

    var getAllCouplets = function () {
        return $http.get('/api/couplets/all');
    }

    var getSongCategories = function () {
        return $http.get('/api/category/song');
    }

    var getMediaCategories = function () {
        return $http.get('/api/category/media');
    }

    var getSong = function (id) {
        return $http.get('/api/songs/'+id);
    }

    var createSong = function (song) {
        return $http.post('/api/songs', song);
    }

    var editSong = function (song) {
        return $http.post('/api/songs/edit',song);
    }

  return {
    getSong:getSong,
    createSong:createSong,
    editSong:editSong,
    getAllUmbrellaTitles:getAllUmbrellaTitles,
    getAllSongTitles:getAllSongTitles,
    getAllSingers:getAllSingers,
    getAllPoets:getAllPoets,
    getAllCouplets:getAllCouplets,
    getSongCategories:getSongCategories,
    getMediaCategories:getMediaCategories,
  };
};