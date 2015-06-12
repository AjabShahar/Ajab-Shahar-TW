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

            var containsAmpersand = function(singer) {
                if(!_.isEmpty(singer)){
                    return  singer.indexOf('&') > 0;
                }
                return false;
            };

            $scope.init = function () {
                if(!_.isEmpty($scope.singers) && $scope.singers.length > 1  ){
                    $scope.multipleSingers = true;
                    $scope.noun = "sing";
                    setTitles();
                    return;
                }
                else if(containsAmpersand($scope.singer)){
                    $scope.multipleSingers = true;
                    $scope.noun = "sing";
                    setTitles();
                    return;
                }
                $scope.multipleSingers = false;
                $scope.noun = "sings";
                setTitles();
            };

            $scope.getSingers = function(){
                return _.isEmpty($scope.singers)?$scope.singer :$scope.singers ;
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
