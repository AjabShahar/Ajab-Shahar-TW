var allSongsApp = angular.module('allSongsApp',['thumbnailModule','youtubeApp','popupSupport','htmlGenerator','sticky', 'headerModule']);

allSongsApp.config(resourceUrlWhiteList);
allSongsApp.factory('contentService', ['$http', contentService]);