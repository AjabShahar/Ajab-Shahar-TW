'use strict';

thumbnailModule.directive("wordWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'js/common/templates/thumbnail/word.html',
        controller:function($scope){
            $scope.shouldBeOpen = false;

            $scope.open = function(){
                $scope.shouldBeOpen = true;
            }

            $scope.onClose = function(){
                $scope.shouldBeOpen = false;
            }
        }
    }
});