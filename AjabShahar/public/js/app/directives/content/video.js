'use strict';

contentModule.directive("videoWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            videoid: '@',
            name:'@',
            singer:'@',
            showcontrols:'@',
            shiftDirection:'@',
            shiftIndex:'@',
        },
        templateUrl:'js/templates/content/video.html',
        controller:function($scope){
            $scope.shift = function(){
                if($scope.shiftDirection && ($scope.shiftDirection=='r'||$scope.shiftDirection=='R'))
                    return 'shiftRight'+$scope.shiftIndex;
                if($scope.shiftDirection && ($scope.shiftDirection=='l'||$scope.shiftDirection=='L'))
                    return 'shiftLeft'+$scope.shiftIndex;
            };
        }
    }
});