var songDetailsApp = angular.module('songDetailsApp',['thumbnailModule','mediaPlayer','htmlGenerator','sticky', 'headerModule','ngAnimate', 'ngTouch']);

songDetailsApp.config(resourceUrlWhiteList);
songDetailsApp.factory('songsContentService', ['$http', songsContentService]);