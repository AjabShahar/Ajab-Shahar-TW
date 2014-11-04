'use strict';

thumbnailModule.directive("songIntroduction", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            videoUrl:'@',
            id:'@',
            audioUrl:'@',
            closeVideo:'&'
        },
        templateUrl:'/user/js/common/templates/songs/introduction.html',
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
            //$scope.$on($scope.stopOn,$stopParam)
        }
    }
});