'use strict';

thumbnailModule.directive("couplet", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            poet:'@',
            id:'@',
            closeVideo:'&'
        },
        templateUrl:'/user/js/common/templates/couplets/introduction.html',
        controller: function($scope) {
            $scope.$watch(function() { return $scope.closeVideo(); }, function(newValue, oldValue) {
                $scope.shouldStopVideo = !newValue;
            });
            //$scope.$on($scope.stopOn,$stopParam)
        }
    }
});