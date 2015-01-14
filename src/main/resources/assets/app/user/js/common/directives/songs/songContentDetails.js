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