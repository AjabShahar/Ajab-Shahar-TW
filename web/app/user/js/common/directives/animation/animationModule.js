'use strict';

var animationModule = angular.module("animationModule", ['ngAnimate']);

animationModule.directive("scroll", function ($window) {
    return function (scope, element, attrs) {
        angular.element($window).bind("scroll", function () {
            var containerTop = $('.main-content').offset().top;
            var windowTop = $(window).scrollTop();
            if (windowTop > containerTop) {

                $('.left-nav').css('top', (windowTop - containerTop)+ 50);
            }
            else {
                $('.left-nav').css('top', -5);
            }

            scope.$apply();
        });
    };

});

