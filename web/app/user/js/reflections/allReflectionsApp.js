var allReflectionsApp = angular.module('allReflectionsApp', ['thumbnailModule', 'mediaPlayer',  'htmlGenerator', 'headerModule', 'infinite-scroll','filterModule']);



allReflectionsApp.config(resourceUrlWhiteList);
allReflectionsApp.factory('reflectionsContentService', ['$http', reflectionsContentService]);
