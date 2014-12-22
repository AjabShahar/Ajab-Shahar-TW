var songsAdminApp = angular.module('songsAdminApp', ['multi-select', 'textAngular']);
songsAdminApp.factory('songContentService', ['$http', songContentService]);

songsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });



