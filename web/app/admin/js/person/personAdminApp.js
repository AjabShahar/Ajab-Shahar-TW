var personAdminApp = angular.module('personAdminApp', ['multi-select', 'adminCommon']);
personAdminApp.factory('contentService', ['$http', contentService]);

personAdminApp.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
    });
});
