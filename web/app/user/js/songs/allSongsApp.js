var allSongsApp = angular.module('allSongsApp', ['thumbnailModule', 'mediaPlayer', 'popupSupport', 'htmlGenerator', 'headerModule', 'filterModule']);

allSongsApp.config(resourceUrlWhiteList);
allSongsApp.factory('songsContentService', ['$http', '$q',songsContentService]);
