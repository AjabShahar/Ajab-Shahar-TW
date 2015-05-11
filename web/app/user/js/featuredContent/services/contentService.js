featuredContentApp.factory('mainLandingContentService', ['$http',function ($http) {
    var getMainLandingPageThumbnails = function () {
        return {
            songs: $http.get('/api/songs?content=featured'),
            words: $http.get('/api/words?showOnMainLandingPage=true'),
            reflections: $http.get('/api/reflections?content=featured')
        };
    };

    var getSongsLandingPageThumbnails = function () {
        return $http.get('/api/songs?content=songsFeatured');
    };

    return {
        getMainLandingPageThumbnails: getMainLandingPageThumbnails,
        getSongsLandingPageThumbnails: getSongsLandingPageThumbnails

    };
}]);
