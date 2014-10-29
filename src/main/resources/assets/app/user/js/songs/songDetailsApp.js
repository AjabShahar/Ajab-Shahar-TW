var songDetailsApp = angular.module('allSongsApp',['thumbnailModule','youtubeApp','htmlGenerator','sticky', 'headerModule','carouselModule']);

songDetailsApp.config(resourceUrlWhiteList);
songDetailsApp.factory('contentService', ['$http', contentService]);