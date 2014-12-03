'use strict';

thumbnailModule.directive("asCarousel", function() {
    return {
        restrict: 'A',
        scope: {},
        link: function ($scope, $elem, $attrs) {
            $elem.jcarousel();
        }
    }
});