var glossaryApp = angular.module('glossaryApp', ['headerModule']);

glossaryApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);
glossaryApp.factory('contentService', ['$http', contentService]);

glossaryApp.config(["$locationProvider", function ($locationProvider) {
    $locationProvider.html5Mode(true);
}]);