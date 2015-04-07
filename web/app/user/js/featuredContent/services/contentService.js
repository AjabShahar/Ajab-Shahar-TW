var contentService = function ($http) {
  var getMainLandingPageThumbnails = function () {
    return {
      songs: $http.get('/api/songs/getPublishedSongs'),
      words: $http.get('/api/words?showOnMainLandingPage=true'),
      reflections: $http.get('/api/reflections?content=featured')
    };
  };

  var getSongsLandingPageThumbnails = function () {
    return $http.get('/api/songs/getPublishedSongs');
  };

  var getWordsLandingPageContent = function () {
    return $http.get('/api/words?showOnWordsLandingPage=true');
  };

  var getGlossaryPageContent = function () {
    return $http.get('/api/words');
  };

  return {
    getMainLandingPageThumbnails: getMainLandingPageThumbnails,
    getSongsLandingPageThumbnails: getSongsLandingPageThumbnails,
    getWordsLandingPageContent: getWordsLandingPageContent,
    getGlossaryPageContent: getGlossaryPageContent
  };
};
