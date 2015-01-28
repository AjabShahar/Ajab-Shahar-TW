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
            notes:'@',
            detailsService:'='
        },
        templateUrl:'/user-js/common/templates/songs/songContentDetails.html',
        controller: function($scope) {
            $scope.isAboutVisible = false;
            $scope.isNotesVisible = false;
            $scope.isVideoSong = false;
            $scope.showVideo = false;
            $scope.showAudio = false;

            $scope.hideContents = function(){
                return $scope.detailsService.shouldHideDetails;
            }

            $scope.isAudioOrVideo = function(){
                if(Boolean($scope.videoId)){
                    $scope.showVideo = true;
                    $scope.isVideoSong = true;
                }
                else {
                    $scope.showAudio = true;
                }
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

            $scope.showAbout = function(){
                if($scope.isAboutVisible){
                    $scope.isAboutVisible = false;
                    return;
                }

                $scope.isAboutVisible = true;
                $scope.isNotesVisible = false;
            }

            $scope.showNotes = function(){
                if($scope.isNotesVisible){
                    $scope.isNotesVisible = false;
                    return;
                }
                
                $scope.isNotesVisible = true;
                $scope.isAboutVisible = false;
            }
        }
    }
});