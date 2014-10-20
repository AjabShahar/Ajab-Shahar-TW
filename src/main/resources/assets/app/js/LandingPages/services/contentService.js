var contentService = function ($http) {
  var getMainLandingPageThumbnails = function () {
    return $http.get('/api/LandingPages/mainPage');
  };

  var getSongsLandingPageThumbnails = function () {
    return $http.get('/api/LandingPages/songsPage');
  };

  return {
    getMainLandingPageThumbnails: getMainLandingPageThumbnails,
    getSongsLandingPageThumbnails: getSongsLandingPageThumbnails,
  };
};