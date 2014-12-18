var songsAdminApp = angular.module('songsAdminApp', ['multi-select']);

songsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });



