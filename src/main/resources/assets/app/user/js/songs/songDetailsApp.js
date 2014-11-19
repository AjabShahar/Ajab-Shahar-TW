var songDetailsApp = angular.module('songDetailsApp',['thumbnailModule','mediaPlayer','htmlGenerator','sticky', 'headerModule','ngAnimate', 'ngTouch','carouselModule']);

songDetailsApp.config(resourceUrlWhiteList);
songDetailsApp.factory('songsContentService', ['$http', songsContentService]);