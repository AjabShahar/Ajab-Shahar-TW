var wordsFeaturedContentApp = angular.module('wordsFeaturedContentApp', ['thumbnailModule', 'headerModule']);

wordsFeaturedContentApp.config(resourceUrlWhiteList);

wordsFeaturedContentApp.config(["$locationProvider", function ($locationProvider) {
    $locationProvider.html5Mode(true);
}]);

wordsFeaturedContentApp.factory('contentService', ['$http', contentService]);

wordsFeaturedContentApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);
