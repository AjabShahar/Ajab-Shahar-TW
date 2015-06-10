'use strict';

thumbnailModule.directive("contentDetails", function () {
    return {
        restrict: 'E',
        scope: {
            content:"="
        },
        templateUrl: '/user/js/common/directives/contentDetails/contentDetails.html',
        controller: function ($scope, $location) {
            $scope.showVideo = false;
            $scope.showAudio = false;
            $scope.hasText = false;
            $scope.sharingVisible = false;
            $scope.isAboutVisible = false;
            $scope.url;

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

            $scope.toggleShare = function(){
                $scope.sharingVisible = !$scope.sharingVisible;
            };

            $scope.hasText = function(){
                return !_.isEmpty($scope.content.textSections);
            };

            $scope.createUrl = function(resource){
              return $scope.url + resource;
            };

            $scope.createURLForImage = function(imageURL){
                if(imageURL){
                    if(imageURL.includes("http")){
                        return imageURL;
                    }
                }
                else{
                    return $scope.url + imageURL;
                }
            };

            var init = function(){
                if(!_.isEmpty($scope.content)){
                    $scope.showVideo = !!(!$scope.hasText() && $scope.content.videoId);
                    $scope.showAudio = !!(!$scope.hasText() && !$scope.content.videoId && $scope.content.audioId);
                }
                $scope.url = $location.host();
            };

            $scope.$watch("content",function(){
                init();
            })
        }
    }
});
