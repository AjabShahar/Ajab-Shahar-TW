var contentService = function ($http) {
  var getLandingPageThumbnails = function (resource) {
    return $http.get('/api/' + resource);
  };

  return {
    getLandingPageThumbnails: getLandingPageThumbnails,
  };
};