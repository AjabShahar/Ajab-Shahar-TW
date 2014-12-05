var adminApp = angular.module('adminApp', ['textAngular']);

adminApp.factory('contentService', ['$http', contentService]);
adminApp.factory('nameService', [nameService]);

adminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
         requireBase: false
       });
 });

