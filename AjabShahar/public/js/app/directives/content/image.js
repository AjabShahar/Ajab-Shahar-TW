'use strict';

contentModule.directive("imageWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imgSrc:'@',
            shiftDirection:'@',
            shiftIndex:'@',
        },
        templateUrl:'js/templates/content/image.html',
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