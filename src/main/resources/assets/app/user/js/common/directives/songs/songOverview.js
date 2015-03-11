'use strict';

thumbnailModule.directive("songOverview", function() {
    return {
        restrict: 'E',
        scope: {
            englishTranslation:'@',
            englishTransliteration:'@',
            singer:'@',
            poet:'@',
            videoUrl:'@',
            id:'@',
            audioUrl:'@',
            closeVideo:'&',
            noun: '@',
            words:'=',
        },
        templateUrl:'/user-js/common/templates/songs/songOverview.html',
        controller: function($scope) {
            $scope.hasAudioAndVideo = false;
            $scope.showVideo = false;
            $scope.showAudio = false;

            $scope.$watch(function() { return $scope.closeVideo(); }, function(newValue, oldValue) {
                $scope.shouldStopVideo = !newValue;
            });

            $scope.isVideo = function(){
                return Boolean($scope.videoUrl);
            };

            $scope.isAudio = function(){
                return !$scope.isVideo();
            };

            $scope.isAudioOrVideo = function(){
                if(Boolean($scope.videoUrl)){
                    $scope.showVideo = true;
                    (Boolean($scope.audioUrl)) ? $scope.hasAudioAndVideo = true : $scope.hasAudioAndVideo = false;
                }
                else {
                    $scope.showAudio = true;
                }
            }

            $scope.toggleVideoOrAudio = function(){
                if($scope.showVideo){
                    $scope.showAudio = true;
                    $scope.showVideo = false;
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