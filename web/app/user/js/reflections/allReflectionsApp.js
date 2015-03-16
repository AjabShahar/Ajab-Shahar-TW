var allReflectionsApp = angular.module('allReflectionsApp',['thumbnailModule','mediaPlayer','popupSupport','htmlGenerator', 'headerModule', 'infinite-scroll','filterModule']);

allReflectionsApp.config(resourceUrlWhiteList);
allReflectionsApp.factory('reflectionsContentService', ['$http', reflectionsContentService]);
