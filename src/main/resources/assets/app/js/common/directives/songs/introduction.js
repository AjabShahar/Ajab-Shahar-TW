'use strict';

thumbnailModule.directive("songIntroduction", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            url:'@',
            openVideo:'&'
        },
        templateUrl:'/js/common/templates/songs/introduction.html',
        controller: function($scope) {
            $scope.$watch(function() { return $scope.openVideo(); }, function(newValue, oldValue) {
                $scope.shouldStopVideo = !newValue;
            });
            //$scope.$on($scope.stopOn,$stopParam)
        }
    }
});