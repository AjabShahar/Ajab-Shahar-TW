var allSongsApp = angular.module('allSongsApp',['thumbnailModule','mediaPlayer','popupSupport','htmlGenerator', 'headerModule', 'infinite-scroll']);

allSongsApp.config(resourceUrlWhiteList);
allSongsApp.factory('songsContentService', ['$http', songsContentService]);