'use strict';

contentModule.directive("videoWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            videoid: '@',
            name:'@',
            singer:'@',
            showcontrols:'@',
            shiftStyle:'&',
        },
        templateUrl:'js/templates/content/video.html',
        controller:function($scope){
            $scope.shiftRight = function(num){
                return 'shiftRight'+num;
            };
        }
    }
});