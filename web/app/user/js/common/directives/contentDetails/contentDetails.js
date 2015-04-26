'use strict';

thumbnailModule.directive("contentDetails", function () {
    return {
        restrict: 'E',
        scope: {
            content:"="
        },
        templateUrl: '/user/js/common/directives/contentDetails/contentDetails.html',
        controller: function ($scope) {
            $scope.isAboutVisible = false;
            $scope.hasAudioAndVideo = false;
            $scope.showVideo = false;
            $scope.showAudio = false;
            $scope.showText = false;

            $scope.isAudioOrVideo = function () {
                if (Boolean($scope.content.videoId)) {
                    $scope.showVideo = true;
                    (Boolean($scope.content.audioUrl)) ? $scope.hasAudioAndVideo = true : $scope.hasAudioAndVideo = false;
                }
                else {
                    $scope.showAudio = true;
                }
            };

            $scope.toggleVideoOrAudio = function () {
                if ($scope.showVideo) {
                    $scope.showAudio = true;
                    $scope.showVideo = false;
                    //$scope.shouldStopVideo();

                }
                else {
                    $scope.showAudio = false;
                    $scope.showVideo = true;
                }
            };

            //$scope.shouldStopVideo = function () {
            //    return $scope.detailsService.isClosed($scope.id);
            //};

            $scope.showAbout = function () {
                if ($scope.isAboutVisible) {
                    $scope.isAboutVisible = false;
                    return;
                }

                $scope.isAboutVisible = true;
            };

            $scope.showText = function(){
                return !_.isEmpty($scope.content.textSections);
            };

            var init = function(){
                if(!_.isEmpty($scope.content)){
                    $scope.showVideo = !!(!$scope.showText() && $scope.content.videoId);
                    $scope.showAudio = !!(!$scope.showText() && !$scope.content.videoId && $scope.content.audioId);
                }
            };

            $scope.$watch("content",function(){
                init();
            })
        }
    }
});
