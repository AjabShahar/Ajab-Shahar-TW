'use strict';

thumbnailModule.directive("songThumbnail", function ($timeout) {
    return {
        replace: true,
        restrict: 'E',
        scope: {
            englishTransliteration: '@',
            englishTranslation: '@',
            singer: '@',
            singers: '@',
            imgSrc: '@',
            url: '@',
            categoryName: '@',
            duration: '@',
            poet: '@',
            customStyle: '@',
            contentId: '@',
            id: '@',
            open: '&' //open click handler ~ callback to controller
        },
        templateUrl: '/user/js/common/templates/songs/songThumbnail.html',
        controller: function ($scope) {
            $scope.textRepresentation = 'Transliteration';
            $scope.shouldShowDetails = true;
            $scope.noun = "";
            $scope.multipleSingers = false;

            $scope.onTimeOut = function () {
                $scope.hideDetails();
            };

            $timeout($scope.onTimeOut, 1000);

            $scope.$on('contentTextRepresentation', function (event, data) {
                $scope.textRepresentation = data;
            });

            $scope.getTitle = function () {
                return ($scope.textRepresentation === 'Transliteration') ? $scope.englishTransliteration : $scope.englishTranslation;
            };

            $scope.getSecondTitle = function () {
                return ($scope.textRepresentation === 'Transliteration') ? $scope.englishTranslation : $scope.englishTransliteration;
            };

            $scope.init = function () {
                if ($scope.singers == null || $scope.singers.length <= 0) {
                    $scope.multipleSingers = false;
                    $scope.noun = "sings";
                    return;
                }
                $scope.multipleSingers = true;
                $scope.noun = "sing";
            };

            $scope.showDetails = function () {
                $scope.shouldShowDetails = true;
            };

            $scope.hideDetails = function () {
                $scope.shouldShowDetails = false;
            };

            $scope.init();
        }
    }
});
