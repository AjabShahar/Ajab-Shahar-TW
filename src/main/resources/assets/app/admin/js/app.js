var adminApp = angular.module('adminApp',[]);

adminApp.factory('contentService', ['$http', contentService]);

adminApp.config(function($locationProvider) {
       $locationProvider.html5Mode({
         enabled: true,
         requireBase: false
       });
 });

/*
Cookie-based Authentication in AngularJS : http://ionicframework.com/blog/angularjs-authentication/
adminApp.config(['$httpProvider', function($httpProvider) {
   $httpProvider.defaults.withCredentials = true;
}])
*/
