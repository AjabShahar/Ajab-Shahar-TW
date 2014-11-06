var allSongsApp = angular.module('allSongsApp',['thumbnailModule','mediaPlayer','popupSupport','htmlGenerator','sticky', 'headerModule','carouselModule', 'infinite-scroll']);


allSongsApp.config(resourceUrlWhiteList);
allSongsApp.factory('songsContentService', ['$http', songsContentService]);

allSongsApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);