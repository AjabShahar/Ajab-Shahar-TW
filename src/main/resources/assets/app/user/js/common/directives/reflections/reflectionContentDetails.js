'use strict';

thumbnailModule.directive("reflectionContentDetails", function() {
    return {
        restrict: 'E',
        scope: {
            videoId:'@',
            audioUrl:'@',
            id:'@',
            transcript:'@',
            detailsService:'='
        },
        templateUrl:'/user-js/common/templates/reflections/reflectionContentDetails.html',
        controller: function($scope) {
            $scope.isVideoSong = false;
            $scope.showVideo = false;
            $scope.showAudio = false;
            $scope.showTranscript = false;

            $scope.isAudioOrVideo = function(){
                if(Boolean($scope.videoId)){
                    $scope.showVideo = true;
                    $scope.isVideoSong = true;
                }
                else {
                    $scope.showAudio = true;
                }
                if($scope.transcript.length > 0)
                   $scope.showTranscript = true;
            }

            $scope.toggleVideoOrAudio = function(){
                if($scope.showVideo){
                    $scope.showAudio = true;
                    $scope.showVideo = false;
                    $scope.shouldStopVideo();
                    return;
                }
                else{
                    $scope.showAudio = false;
                    $scope.showVideo = true;
                }
            }


            $scope.shouldStopVideo = function(){
                return $scope.detailsService.isClosed($scope.id);
            }
        }
    }
});