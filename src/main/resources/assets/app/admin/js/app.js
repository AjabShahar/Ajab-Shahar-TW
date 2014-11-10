var adminApp = angular.module('adminApp',['headerModule', 'a8m.unique']);

adminApp.factory('contentService', ['$http', contentService]);
adminApp.factory('nameService', [nameService]);

adminApp.config(function($locationProvider) {
       $locationProvider.html5Mode(true);
 });