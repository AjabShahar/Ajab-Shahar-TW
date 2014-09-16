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
            $scope.shift = function(direction,num){
                if(direction && (direction=='r'||direction=='R'))
                    return 'shiftRight'+num;
                if(direction && (direction=='l'||direction=='L'))
                    return 'shiftLeft'+num;
            };
        }
    }
});