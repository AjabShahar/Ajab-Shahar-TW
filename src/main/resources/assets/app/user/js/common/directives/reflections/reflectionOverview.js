'use strict';

thumbnailModule.directive("reflectionOverview", function() {
    return {
        restrict: 'E',
        scope: {
            videoUrl:'@',
            id:'@',
            audioUrl:'@',
            closeVideo:'&'
        },
        templateUrl:'/user-js/common/templates/reflections/reflectionOverview.html',
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