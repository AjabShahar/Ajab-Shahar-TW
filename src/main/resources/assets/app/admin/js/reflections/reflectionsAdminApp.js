var reflectionsAdminApp = angular.module('reflectionsAdminApp', ['textAngular','adminCommon']);
reflectionsAdminApp.factory('reflectionContentService', ['$http', reflectionContentService]);

reflectionsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true
       });
 });