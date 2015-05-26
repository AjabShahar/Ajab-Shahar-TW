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
        controller: function ($scope,$rootScope) {
            $scope.bubbleShowClass = "thumbdetails";
            $scope.textRepresentation = 'Transliteration';
            $scope.shouldShowDetails = true;
            $scope.noun = "";
            $scope.multipleSingers = false;

            $scope.onTimeOut = function () {
                $scope.hideDetails();
            };

            $timeout($scope.onTimeOut, 1000);

            var setTitles = function(){
                $scope.primaryTitle =   ($scope.textRepresentation === 'Transliteration') ? $scope.englishTransliteration : $scope.englishTranslation;
                $scope.secondaryTitle =($scope.textRepresentation === 'Transliteration') ? $scope.englishTranslation : $scope.englishTransliteration;
            };

            $rootScope.$watch('contentTextRepresentation', function ( value) {
                $scope.textRepresentation = value;
                setTitles();
            });

            $scope.init = function () {
                if ($scope.singers == null || $scope.singers.length <= 0) {
                    $scope.multipleSingers = false;
                    $scope.noun = "sings";
                    return;
                }
                $scope.multipleSingers = true;
                $scope.noun = "sing";
                setTitles();
            };

            $scope.showDetails = function () {
                $scope.bubbleShowClass = "thumbdetails";
            };


            $scope.hideDetails = function() {
                console.log("in here");
                $scope.bubbleShowClass = "thumbdetails collapse";
            }

            $scope.init();
        }
    }
});
