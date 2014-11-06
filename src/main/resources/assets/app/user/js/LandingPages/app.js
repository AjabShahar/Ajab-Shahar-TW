var landingPagesApp = angular.module('landingPagesApp',['angular-parallax','thumbnailModule','mediaPlayer','popupSupport','htmlGenerator','sticky','headerModule']);

landingPagesApp.config(resourceUrlWhiteList);
landingPagesApp.factory('contentService', ['$http', contentService]);

landingPagesApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);