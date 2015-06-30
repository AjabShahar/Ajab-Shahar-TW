'use strict';

var animationModule = angular.module("animationModule", ['ngAnimate']);

animationModule.directive("scroll", function ($window) {
    return function (scope, element, attrs) {
        angular.element($window).bind("scroll", function () {
            var containerTop = $('.main-content').offset().top;
            var footerTop = $('.footer-container').offset().top;
            var windowTop = $(window).scrollTop();
            var position = (windowTop - containerTop)+ 50
            if (windowTop > containerTop &&  (footerTop - position) > 350) {

                $('.left-nav').css('top', (windowTop - containerTop)+ 50);
            }
            else if (footerTop - position > 350){
                $('.left-nav').css('top', -5);
            }

            scope.$apply();
        });
    };

});

