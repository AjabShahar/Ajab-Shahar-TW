'use strict';

thumbnailModule.directive("wordThumbnail", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            introductionSummary:'@',
            customStyle:'@',
            overlayId:'@',
            id:'@',
            categoryName:'@',
        },
        templateUrl:'/user/js/common/templates/thumbnail/wordThumbnail.html',
        controller:function($scope){
            $scope.shouldBeOpen = false;
            $scope.shouldShowDetails = false;

            $scope.open = function(){
                $scope.shouldBeOpen = true;
            }

            $scope.onClose = function(){
                $scope.shouldBeOpen = false;
            }

            $scope.showDetails = function(){
                $scope.shouldShowDetails = true;
            }

            $scope.hideDetails = function(){
                $scope.shouldShowDetails = false;
            }
        }
    }
});