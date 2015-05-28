var songContentService = function ($http) {
    var getUmbrellaTitles = function () {
        return $http.get('/api/title/umbrella');
    };

    var getSongTitles = function () {
        return $http.get('/api/title/song');
    };

    var getSingers = function () {
        return $http.get('/api/people/summary?show=all&role=Singer');
    };

    var getPoets = function () {
        return $http.get('/api/people/summary?show=all&role=Poet');
    };

    var getCouplets = function () {
        return $http.get('/api/couplets/all');
    };

    var getGenres = function () {
        return $http.get('/api/genres');
    };

    var getSongCategories = function () {
        return $http.get('/api/category/song');
    };

    var getMediaCategories = function () {
        return $http.get('/api/category/media');
    };

    var getSong = function (songId) {
        return $http.get('/api/songs/' + songId);
    };

    var createSong = function (song) {
        return $http.post('/api/songs', song);
    };

    var editSong = function (song) {
        return $http.post('/api/songs/edit', song);
    };

    var getWords = function () {
        return $http.get('/api/words');
    };

    var getGatherings = function () {
        return $http.get('/api/gatherings');
    };

    var getReflections = function () {
        return $http.get('/api/reflections');
    };

    return {
        getSong: getSong,
        createSong: createSong,
        editSong: editSong,
        getUmbrellaTitles: getUmbrellaTitles,
        getSongTitles: getSongTitles,
        getSingers: getSingers,
        getPoets: getPoets,
        getCouplets: getCouplets,
        getSongCategories: getSongCategories,
        getMediaCategories: getMediaCategories,
        getGenres: getGenres,
        getWords: getWords,
        getGatherings: getGatherings,
        getReflections: getReflections
    };
};
