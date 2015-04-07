var contentService = function ($http) {
    var getScreenOptions = function () {
        return $http.get('/api/SplashScreenOptions');
    };

    return {
        getScreenOptions: getScreenOptions,
    };
};

splashScreenOptionsApp.factory('contentService', ['$http', contentService]);
