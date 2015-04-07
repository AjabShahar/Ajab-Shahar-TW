var genresAdminApp = angular.module('genresAdminApp', ['adminCommon']);

genresAdminApp.factory('genreContentService', ['$http', genreContentService]);

genresAdminApp.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});



