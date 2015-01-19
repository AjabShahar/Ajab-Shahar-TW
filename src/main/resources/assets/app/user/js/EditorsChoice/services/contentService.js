var contentService = function ($http) {
  var getMainLandingPageThumbnails = function () {
    var content = {
      songs: $http.get('/api/songs/getPublishedSongs?randomSongsEnabled=true'),
      words: $http.get('/api/words?showOnMainLandingPage=true')
    };
    return content;
  };

  var getSongsLandingPageThumbnails = function () {
    return $http.get('/api/songs/getPublishedSongs?randomSongsEnabled=true');
  };

  var getWordsLandingPageContent = function () {
    return $http.get('/api/words?showOnWordsLandingPage=true');
  };

  return {
    getMainLandingPageThumbnails: getMainLandingPageThumbnails,
    getSongsLandingPageThumbnails: getSongsLandingPageThumbnails,
    getWordsLandingPageContent: getWordsLandingPageContent,
  };
};