var wordDetailsApp = angular.module('wordDetailsApp',['thumbnailModule','htmlGenerator', 'headerModule', 'animationModule','popupSupport']);

wordDetailsApp.factory('wordService', ['$http', wordService]);

wordDetailsApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });

 wordDetailsApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);