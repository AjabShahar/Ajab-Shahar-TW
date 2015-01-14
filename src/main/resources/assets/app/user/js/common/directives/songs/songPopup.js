'use strict';

thumbnailModule.directive("songPopup", function() {
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
            noun: '@'
        },
        templateUrl:'/user/js/common/templates/songs/songPopup.html',
        controller: function($scope) {
            $scope.$watch(function() { return $scope.closeVideo(); }, function(newValue, oldValue) {
                $scope.shouldStopVideo = !newValue;
            });

            $scope.isVideo = function(){
                return $scope.videoUrl!=null && $scope.videoUrl != "null";
            };

            $scope.isAudio = function(){
                return !$scope.isVideo();
            };
        }
    }
});