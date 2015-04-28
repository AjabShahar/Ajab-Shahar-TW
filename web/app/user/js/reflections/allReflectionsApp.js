var allReflectionsApp = angular.module('allReflectionsApp', ['thumbnailModule', 'mediaPlayer',  'htmlGenerator', 'headerModule', 'infinite-scroll']);



allReflectionsApp.config(resourceUrlWhiteList);
allReflectionsApp.factory('reflectionsContentService', ['$http', reflectionsContentService]);
