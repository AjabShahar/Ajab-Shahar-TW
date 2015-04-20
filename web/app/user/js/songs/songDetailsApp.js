var songDetailsApp = angular.module('songDetailsApp', ['thumbnailModule', 'mediaPlayer', 'htmlGenerator', 'headerModule', 'animationModule']);

songDetailsApp.config(resourceUrlWhiteList);
songDetailsApp.factory('songsContentService', ['$http', songsContentService]);

songDetailsApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);

songDetailsApp.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
    });
});

songDetailsApp.directive("scroll", function ($window) {
    return function (scope, element, attrs) {
        angular.element($window).bind("scroll", function () {
            var containerTop = $('.main-content').offset().top;
            var windowTop = $(window).scrollTop();
            if (windowTop > containerTop) {

                $('.left-nav').css('top', (windowTop - containerTop)+ 120);
            }
            else {
                $('.left-nav').css('top', -5);
            }

            scope.$apply();
        });
    };

});
