var contentService = function ($http) {
  var getMainLandingPageThumbnails = function () {
    return $http.get('/api/songs/getsongs');
  };

  var getSongsLandingPageThumbnails = function () {
    return $http.get('/api/songs/landingPage');
  };

  return {
    getMainLandingPageThumbnails: getMainLandingPageThumbnails,
    getSongsLandingPageThumbnails: getSongsLandingPageThumbnails,
  };
};