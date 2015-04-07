var gatheringsAdminApp = angular.module('gatheringsAdminApp', ['adminCommon']);

gatheringsAdminApp.factory('gatheringContentService', ['$http', gatheringContentService]);

gatheringsAdminApp.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});



