var wordsAdminApp = angular.module('wordsAdminApp', ['textAngular', 'multi-select', 'adminCommon','commonApp']);
wordsAdminApp.factory('contentService', ['$http', contentService]);

wordsAdminApp.config(function ($locationProvider) {

});

wordsAdminApp.constant("PAGES", {
    "ADMIN_HOME": "/admin/partials/home.html",
    "EDIT": "/admin/partials/songs/edit.html",
    "DETAILS": "/admin/partials/songs/details.html"
});
