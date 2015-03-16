'use strict';

thumbnailModule.directive("wordThumbnail", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            transliteration:'@',
            translation:'@',
            englishIntroExcerpt:'@',
            customStyle:'@',
            overlayId:'@',
            imgSrc:'@',
            id:'@',
            open:'&',
        },
        templateUrl:'/user/js/common/templates/words/wordThumbnail.html',
        controller:function($scope, $window){
            $scope.shouldBeOpen = false;
            $scope.shouldShowDetails = false;

//            $scope.open = function(){
//                $scope.shouldBeOpen = true;
//            }
//
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
