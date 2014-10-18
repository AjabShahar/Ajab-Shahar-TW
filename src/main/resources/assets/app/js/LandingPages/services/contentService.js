var contentService = function ($http) {
  var getLandingPageThumbnails = function () {
    return $http.get('/api/LandingPagesContent');
  };

  return {
    getLandingPageThumbnails: getLandingPageThumbnails,
  };
};