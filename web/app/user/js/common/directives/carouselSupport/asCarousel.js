'use strict';

thumbnailModule.directive("asCarousel", function ($timeout) {
    return {
        restrict: 'A',
        link: function ($scope, $elem, $attrs) {
            $scope.loadCarousel = function () {
                $elem.jcarousel();
            }
        }
    }
});
