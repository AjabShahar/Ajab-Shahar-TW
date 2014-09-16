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
            $scope.shift = function(direction,num){
                if(direction && (direction=='l'||direction=='L'))
                    return 'shiftLeft'+num;
                return 'shiftRight'+num;
            };
        }
    }
});