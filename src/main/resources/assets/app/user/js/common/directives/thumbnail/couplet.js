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