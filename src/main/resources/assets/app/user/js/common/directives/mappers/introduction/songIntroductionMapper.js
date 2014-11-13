'use strict';

thumbnailModule.directive("songIntroductionMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'=',
            index:'@'
        },
        templateUrl:'/user/js/common/templates/mappers/introduction/songIntroductionMapper.html',
        controller:function($scope){
            $scope.song = {"id":"song_"+$scope.details.id, "videoId":$scope.details.youtubeVideoId,"englishTranslation":$scope.details.songTitle.englishTranslation,
                            "singer":nameService.getName($scope.details.singers[0]) , "audioId":$scope.details.soundCloudTrackID,
                            "poet":nameService.getName($scope.details.poets[0])
                        };
            $scope.init = function(){
                return $scope.showDetailsService.init($scope.index, $scope.song.id);
            }
            $scope.next = function(){
                return $scope.showDetailsService.next($scope.song.id);
            }
            $scope.prev = function(){
                return $scope.showDetailsService.prev($scope.song.id);
            }
            $scope.shouldShow = function(){
                return $scope.showDetailsService.shouldShow($scope.song.id);
            }
            $scope.close = function(){
                return $scope.showDetailsService.onClose($scope.song.id);
            }
            $scope.isClosed = function(){
                return $scope.showDetailsService.isClosed($scope.song.id);
            }
        }
    }
});