'use strict';

contentModule.directive("imageWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            showcontrols:'@',
            imgSrc:'@',
            shiftStyle:'@',
        },
        templateUrl:'js/templates/content/image.html',
        controller:function($scope){
            $scope.shiftRight = function(num){
                return 'shiftRight'+num;
            };
        }
    }
});