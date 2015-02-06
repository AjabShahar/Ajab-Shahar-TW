var reflectionsAdminApp = angular.module('reflectionsAdminApp', ['textAngular']);

reflectionsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });