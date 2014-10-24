var allSongsApp = angular.module('allSongsApp',['thumbnailModule','youtubeApp','popupSupport','htmlGenerator','sticky', 'headerModule','carouselModule']);

allSongsApp.config(resourceUrlWhiteList);
allSongsApp.factory('contentService', ['$http', contentService]);