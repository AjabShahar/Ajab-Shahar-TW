var introductionApp = angular.module('introductionApp',['ngRoute','ngAnimate','youtubeApp']);

introductionApp.config(resourceUrlWhiteList);

introductionApp .config(['$routeProvider',
function($routeProvider) {
     $routeProvider.
       when('/', {
         templateUrl: 'common/index.html',
       }).
       when('/splashScreenVideo', {
         templateUrl: 'splashScreen/video.html',
       }).
       when('/splashScreenAudio', {
         templateUrl: 'splashScreen/audio.html',
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

