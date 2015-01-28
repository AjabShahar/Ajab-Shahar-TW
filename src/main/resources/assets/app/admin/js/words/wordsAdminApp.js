var wordsAdminApp = angular.module('wordsAdminApp', ['textAngular','multi-select']);

wordsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });