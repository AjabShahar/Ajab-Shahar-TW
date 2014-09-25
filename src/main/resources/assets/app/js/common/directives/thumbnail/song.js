'use strict';

thumbnailModule.directive("songWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imgSrc:'@',
            customStyle:'@',
            lightBoxImageUrl:'@',
            overlayId:'@'
        },
        templateUrl:'js/common/templates/thumbnail/song.html',
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