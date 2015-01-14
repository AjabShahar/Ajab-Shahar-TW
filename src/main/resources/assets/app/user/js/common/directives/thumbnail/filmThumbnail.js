'use strict';

thumbnailModule.directive("filmThumbnail", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            context:'@',
            imgSrc:'@',
            name:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'/user-js/common/templates/thumbnail/filmThumbnail.html',
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