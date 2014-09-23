var introductionApp = angular.module('introductionApp',['ngRoute','ngAnimate','youtubeApp']);

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
         templateUrl: 'templates/splashScreen/video.html',
       }).
       when('/splashScreenAudio', {
         templateUrl: 'templates/splashScreen/audio.html',
       });
   }]);

introductionApp.animation('.slide', function() {
    var NgHideClassName = 'ng-hide';
    return {
        beforeAddClass: function(element, className, done) {
            if(className === NgHideClassName) {
                jQuery(element).slideUp(done);
            }
        },
        removeClass: function(element, className, done) {
            if(className === NgHideClassName) {
                jQuery(element).hide().slideDown(1800,done);
            }
        }
    }
});

