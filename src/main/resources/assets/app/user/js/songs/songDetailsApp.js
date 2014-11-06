var songDetailsApp = angular.module('allSongsApp',['thumbnailModule','mediaPlayer','htmlGenerator','sticky', 'headerModule','carouselModule']);

songDetailsApp.config(resourceUrlWhiteList);
songDetailsApp.factory('songsContentService', ['$http', songsContentService]);

songDetailsApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);