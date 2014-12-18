'use strict';

thumbnailModule.directive("songContentDetails", function() {
    return {
        restrict: 'E',
        scope: {
            videoId:'@',
            audioUrl:'@',
            singer:'@',
            poet:'@',
            id:'@',
            detailsService:'='
        },
        templateUrl:'/user/js/common/templates/songs/songContentDetails.html',
        controller: function($scope) {
            $scope.hideContents = function(){
                return $scope.detailsService.shouldHideDetails;
            }

            $scope.isVideo = function(){
                return $scope.videoId!=null && $scope.videoId != "null";
            };

            $scope.isAudio = function(){
                return !$scope.isVideo();
            };

            $scope.shouldStopVideo = function(){
                return $scope.detailsService.isClosed($scope.id);
            }
        }
    }
});