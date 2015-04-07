'use strict';

thumbnailModule.directive("reflectionThumbnail", function () {
    return {
        replace: true,
        restrict: 'E',
        scope: {
            id: '@',
            title: '@',
            verb: '@',
            imgSrc: '@',
            speaker: '@',
            customStyle: '@',
            overlayId: '@',
            open: '&'
        },
        templateUrl: '/user/js/common/templates/reflections/reflectionThumbnail.html',
        controller: function ($scope) {
            $scope.shouldShowDetails = false;

            $scope.showDetails = function () {
                $scope.shouldShowDetails = true;
            };

            $scope.hideDetails = function () {
                $scope.shouldShowDetails = false;
            }
        }
    }
});
