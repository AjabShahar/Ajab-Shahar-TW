'use strict';

contentModule.directive("songWithDetails", function() {
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
        templateUrl:'js/templates/content/song.html',
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