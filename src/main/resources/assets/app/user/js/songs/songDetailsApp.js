var songDetailsApp = angular.module('songDetailsApp',['thumbnailModule','mediaPlayer','htmlGenerator', 'headerModule','animationModule']);

songDetailsApp.config(resourceUrlWhiteList);
songDetailsApp.factory('songsContentService', ['$http', songsContentService]);
songDetailsApp.factory('songThumbnailMapper', [songThumbnailMapper]);

songDetailsApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);

songDetailsApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });