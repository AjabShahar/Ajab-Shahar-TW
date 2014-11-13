'use strict';

thumbnailModule.directive("couplet", function() {
    return {
        restrict: 'E',
        scope: {
            title:'@',
            imgSrc:'@',
            englishTranslationText:'@',
            categoryName:'@',
            customStyle:'@',
            id:'@',
            open:'&',
        },
        templateUrl:'/user/js/common/templates/thumbnail/couplet.html',
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