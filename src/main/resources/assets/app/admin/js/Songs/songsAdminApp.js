var songsAdminApp = angular.module('songsAdminApp', ['multi-select', 'textAngular']);
songsAdminApp.factory('songContentService', ['$http', songContentService]);

songsAdminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
       });
 });

songsAdminApp.constant("PAGES", {
    "ADMIN_HOME": "/admin/home.html",
    "EDIT": "/admin/songs/edit.html",
    "DETAILS": "/admin/songs/details.html"
});