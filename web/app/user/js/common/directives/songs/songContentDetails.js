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
            downloadurl:'@',
            about:'@',
            words:'=',
            detailsService:'='
        },
        templateUrl:'/user/js/common/templates/songs/songContentDetails.html',
        controller: function($scope) {
            $scope.isAboutVisible = false;
            $scope.hasAudioAndVideo = false;
            $scope.showVideo = false;
            $scope.showAudio = false;

            $scope.hideContents = function(){
                return $scope.detailsService.shouldHideDetails;
            };

            $scope.isAudioOrVideo = function(){
                if(Boolean($scope.videoId)){
                    $scope.showVideo = true;
                    (Boolean($scope.audioUrl)) ? $scope.hasAudioAndVideo = true : $scope.hasAudioAndVideo = false;
                }
                else {
                    $scope.showAudio = true;
                }
            };

            $scope.toggleVideoOrAudio = function(){
                if($scope.showVideo){
                    $scope.showAudio = true;
                    $scope.showVideo = false;
                    $scope.shouldStopVideo();

                }
                else{
                    $scope.showAudio = false;
                    $scope.showVideo = true;
                }
            };

            $scope.shouldStopVideo = function(){
                return $scope.detailsService.isClosed($scope.id);
            };

            $scope.showAbout = function(){
                if($scope.isAboutVisible){
                    $scope.isAboutVisible = false;
                    return;
                }

                $scope.isAboutVisible = true;
            }
        }
    }
});
