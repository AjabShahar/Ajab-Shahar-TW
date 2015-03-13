'use strict';

thumbnailModule.directive("gatheringThumbnail", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            location:'@',
            date:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'/user/js/common/templates/thumbnail/gatheringThumbnail.html',
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
