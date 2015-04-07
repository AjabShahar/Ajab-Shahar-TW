var songsAdminApp = angular.module('songsAdminApp', ['multi-select', 'textAngular', 'commonApp', 'adminCommon']);
songsAdminApp.factory('songContentService', ['$http', songContentService]);

songsAdminApp.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
    });
});

songsAdminApp.constant("PAGES", {
    "ADMIN_HOME": "/admin/partials/home.html",
    "EDIT": "/admin/partials/songs/edit.html",
    "DETAILS": "/admin/partials/songs/details.html"
});
