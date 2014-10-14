var contentService = function ($http) {
  var getLandingPageThumbnails = function () {
    return $http.get('/api/MainLandingPageContent');
  };

  return {
    getLandingPageThumbnails: getLandingPageThumbnails,
  };
};

mainLandingPageApp.factory('contentService', ['$http', contentService]);