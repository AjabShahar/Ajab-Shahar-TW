'use strict';

angular.module("word").directive("wordThumbnail", function () {
    return {
        replace: true,
        restrict: 'E',
        scope: {
            transliteration: '@',
            translation: '@',
            englishIntroExcerpt: '@',
            customStyle: '@',
            overlayId: '@',
            imgSrc: '@',
            id: '@',
            open: '&'
        },
        templateUrl: '/user/js/words/directives/templates/wordThumbnail.html',
        controller: function ($scope) {
            $scope.shouldBeOpen = false;
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
