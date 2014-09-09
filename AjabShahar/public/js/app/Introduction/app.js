var introductionApp = angular.module('introductionApp',['ngRoute','ngAnimate']);

introductionApp.config(function($sceDelegateProvider) {
                        $sceDelegateProvider.resourceUrlWhitelist([
                          // Allow same origin resource loads.
                          'self',
                          // Allow loading from our assets domain.  Notice the difference between * and **.
                          'https%3A%2F%2Fwww.youtube.com%2**',
                          'https://www.youtube.com/**',
                        ]);
                       });

introductionApp .config(['$routeProvider',
function($routeProvider) {
     $routeProvider.
       when('/splashScreenVideo', {
         templateUrl: 'js/templates/splash-screen-video.html',
       }).
       when('/splashScreenAudio', {
         templateUrl: 'js/templates/splash-screen-audio.html',
       });
   }]);