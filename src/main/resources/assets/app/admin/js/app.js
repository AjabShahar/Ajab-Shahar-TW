var adminApp = angular.module('adminApp',[]);

adminApp.factory('contentService', ['$http', contentService]);
adminApp.factory('nameService', [nameService]);

adminApp.config(function($locationProvider) {
       $locationProvider.html5Mode(true);
 });