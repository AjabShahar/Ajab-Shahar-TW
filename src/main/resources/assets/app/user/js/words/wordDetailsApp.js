var wordDetailsApp = angular.module('wordDetailsApp',['thumbnailModule','htmlGenerator', 'headerModule', 'animationModule']);


wordDetailsApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });