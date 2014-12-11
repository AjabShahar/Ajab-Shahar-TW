'use strict';

thumbnailModule.directive("songThumbnail", function() {
    return {
        replace : true,
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
            contentId:'@',
            id:'@',
            open: '&', //open click handler ~ callback to controller
        },
        templateUrl:'/user/js/common/templates/thumbnail/songThumbnail.html',
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