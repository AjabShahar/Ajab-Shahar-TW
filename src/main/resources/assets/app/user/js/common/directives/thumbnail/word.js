'use strict';

thumbnailModule.directive("word", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            contextualMeaning:'@',
            customStyle:'@',
            overlayId:'@',
            id:'@',
            categoryName:'@',
        },
        templateUrl:'/user/js/common/templates/thumbnail/word.html',
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