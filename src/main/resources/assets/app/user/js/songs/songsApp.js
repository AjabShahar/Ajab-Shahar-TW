var songsApp = angular.module('songsApp',['ngRoute','animationModule','thumbnailModule','mediaPlayer','popupSupport','htmlGenerator', 'headerModule', 'infinite-scroll','filterModule']);

songsApp.config(resourceUrlWhiteList);
songsApp.config(['$routeProvider',
function($routeProvider) {
     $routeProvider.
       when('/allSongs', {
         templateUrl: 'songs/allSongs.html',
       }).
       when('/details', {
         templateUrl: 'songs/details.html',
       });
   }]);
songsApp.factory('songsContentService', ['$http', songsContentService]);
songsApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);