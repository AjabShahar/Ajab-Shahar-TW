'use strict';

thumbnailModule.directive("coupletWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            title:'@',
            imgSrc:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'/user/js/common/templates/thumbnail/couplet.html',
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