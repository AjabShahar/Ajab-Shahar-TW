var allSongsApp = angular.module('allSongsApp', ['thumbnailModule', 'mediaPlayer', 'popupSupport', 'htmlGenerator', 'headerModule', 'filterModule']);

allSongsApp.config(resourceUrlWhiteList);

allSongsApp.config(["$locationProvider", function ($locationProvider) {
    $locationProvider.html5Mode(true);
}]);

allSongsApp.factory('songsContentService', ['$http', '$q',songsContentService]);
