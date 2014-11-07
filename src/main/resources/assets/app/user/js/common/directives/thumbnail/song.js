'use strict';

thumbnailModule.directive("song", function() {
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
            id:'@',
            open: '&', //open click handler ~ callback to controller
        },
        templateUrl:'/user/js/common/templates/thumbnail/song.html',
        controller:function($scope){
            $scope.shouldShowDetails = false;

            $scope.showDetails = function(){
                $scope.shouldShowDetails = true;
            }

            $scope.hideDetails = function(){
                $scope.shouldShowDetails = false;
            }
        }
    }
});