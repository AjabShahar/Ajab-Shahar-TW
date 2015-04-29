'use strict';

thumbnailModule.directive("contentDetails", function () {
    return {
        restrict: 'E',
        scope: {
            content:"="
        },
        templateUrl: '/user/js/common/directives/contentDetails/contentDetails.html',
        controller: function ($scope) {
            $scope.showVideo = false;
            $scope.showAudio = false;
            $scope.hasText = false;

            $scope.hasAudioAndVideo = function () {
                return $scope.content.videoId && $scope.content.audioId;
            };

            $scope.toggleVideoOrAudio = function () {
                if ($scope.showVideo) {
                    $scope.showAudio = true;
                    $scope.showVideo = false;
                }
                else if($scope.showAudio){
                    $scope.showAudio = false;
                    $scope.showVideo = true;
                }
            };

            $scope.toggleAbout = function () {
                $scope.isAboutVisible = !$scope.isAboutVisible;
            };

            $scope.hasText = function(){
                return !_.isEmpty($scope.content.textSections);
            };

            var init = function(){
                if(!_.isEmpty($scope.content)){
                    $scope.showVideo = !!(!$scope.hasText() && $scope.content.videoId);
                    $scope.showAudio = !!(!$scope.hasText() && !$scope.content.videoId && $scope.content.audioId);
                }
            };

            $scope.$watch("content",function(){
                init();
            })
        }
    }
});
