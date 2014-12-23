var adminApp = angular.module('adminApp',[]);

adminApp.factory('contentService', ['$http', contentService]);

adminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
         requireBase: false
       });
 });

