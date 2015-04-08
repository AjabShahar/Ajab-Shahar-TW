var reflectionsAdminApp = angular.module('reflectionsAdminApp', ['textAngular','multi-select', 'adminCommon']);
reflectionsAdminApp.factory('reflectionContentService', ['$http', reflectionContentService]);

reflectionsAdminApp.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true
    });
});
