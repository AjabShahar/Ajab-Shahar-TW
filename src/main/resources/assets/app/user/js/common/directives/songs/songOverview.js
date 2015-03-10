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
            $scope.$watch(function() { return $scope.closeVideo(); }, function(newValue, oldValue) {
                $scope.shouldStopVideo = !newValue;
            });

            $scope.isVideo = function(){
                return Boolean($scope.videoUrl);
            };

            $scope.isAudio = function(){
                return !$scope.isVideo();
            };
        }
    }
});