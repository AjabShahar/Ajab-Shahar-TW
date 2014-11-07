'use strict';

thumbnailModule.directive("songContent", function() {
    return {
        restrict: 'E',
        scope: {
            song:'=',
        },
        templateUrl:'/user/js/common/templates/songs/content.html',
        controller: function($scope) {
            $scope.closeVideo = function(){
                return $scope.song.shouldCloseVideo;
            }

            $scope.isVideo = function(){
                return $scope.song.videoId!=null && $scope.song.videoId != "null";
            };

            $scope.isAudio = function(){
                return !$scope.isVideo();
            };
        }
    }
});