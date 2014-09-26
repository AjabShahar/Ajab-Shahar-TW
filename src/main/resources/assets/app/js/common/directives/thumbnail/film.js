'use strict';

thumbnailModule.directive("filmWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'js/common/templates/thumbnail/film.html',
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