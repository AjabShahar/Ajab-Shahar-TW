'use strict';

thumbnailModule.directive("unknownFormat", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            description:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'/user-js/common/templates/thumbnail/unknown.html',
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