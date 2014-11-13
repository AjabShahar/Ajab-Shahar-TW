'use strict';

thumbnailModule.directive("word", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            imgSrc:'@',
            contextualMeaning:'@',
            customStyle:'@',
            overlayId:'@'
        },
        templateUrl:'/user/js/common/templates/thumbnail/word.html',
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