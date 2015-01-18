var editorsChoiceBasicApp = angular.module('editorsChoiceBasicApp',['thumbnailModule', 'headerModule']);

editorsChoiceBasicApp.config(resourceUrlWhiteList);
editorsChoiceBasicApp.factory('contentService', ['$http', contentService]);

editorsChoiceBasicApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);