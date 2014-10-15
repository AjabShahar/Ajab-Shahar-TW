'use strict';

thumbnailModule.directive("songWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imgSrc:'@',
            url:'@',
            categoryName:'@',
            duration:'@',
            poet:'@',
            customStyle:'@',
            overlayId:'@',
            open: '&', //open click handler ~ callback to controller
        },
        templateUrl:'/js/common/templates/thumbnail/song.html',
        controller:function($scope){
//            $scope.shouldBeOpen = false;
            $scope.shouldShowDetails = false;
//            $scope.open = function(){
//                $scope.shouldBeOpen = true;
//            }

//            $scope.onClose = function(){
//                $scope.shouldBeOpen = false;
//            }

            $scope.showDetails = function(){
                $scope.shouldShowDetails = true;
            }

            $scope.hideDetails = function(){
                $scope.shouldShowDetails = false;
            }
        }
    }
});