var wordsAdminApp = angular.module('wordsAdminApp', ['textAngular','multi-select','adminCommon']);
wordsAdminApp.factory('contentService', ['$http', contentService]);

wordsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });

wordsAdminApp.constant("PAGES", {
    "ADMIN_HOME": "/admin/home.html",
    "EDIT": "/admin/songs/edit.html",
    "DETAILS": "/admin/songs/details.html"
});