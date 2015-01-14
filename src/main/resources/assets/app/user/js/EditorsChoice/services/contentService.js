var contentService = function ($http) {
  var getMainLandingPageThumbnails = function () {
    return $http.get('/api/songs/getPublishedSongs?randomSongsEnabled=true');
  };

  var getSongsLandingPageThumbnails = function () {
    return $http.get('/api/songs/getPublishedSongs?randomSongsEnabled=true');
  };

  var getWordsLandingPageContent = function () {
    // words/getPublishedSongs
    return $http.get('/api/words');
  };

  return {
    getMainLandingPageThumbnails: getMainLandingPageThumbnails,
    getSongsLandingPageThumbnails: getSongsLandingPageThumbnails,
    getWordsLandingPageContent: getWordsLandingPageContent,
  };
};