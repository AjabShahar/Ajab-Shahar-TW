var wordsAdminApp = angular.module('wordsAdminApp', ['textAngular']);

wordsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });