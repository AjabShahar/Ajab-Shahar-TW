var adminApp = angular.module('adminApp',['ngRoute']);

adminApp.config(['$routeProvider',
function($routeProvider) {
     $routeProvider.
       when('/edit/:id', {
         templateUrl: 'partials/songDetails/edit.html'
       });
   }]);