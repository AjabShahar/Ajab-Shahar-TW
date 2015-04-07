var wordsFeaturedContentApp = angular.module('wordsFeaturedContentApp', ['thumbnailModule', 'headerModule']);

wordsFeaturedContentApp.config(resourceUrlWhiteList);
wordsFeaturedContentApp.factory('contentService', ['$http', contentService]);

wordsFeaturedContentApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);
