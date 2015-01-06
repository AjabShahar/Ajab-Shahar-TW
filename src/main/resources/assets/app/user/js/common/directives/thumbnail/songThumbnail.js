'use strict';

thumbnailModule.directive("songThumbnail", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            englishTransliteration:'@',
            englishTranslation:'@',
            singer:'@',
            singers:'@',
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
            $scope.textRepresentation = 'Transliteration';
            $scope.shouldShowDetails = true;

            $scope.$on('contentTextRepresentation',function(event,data){
                $scope.textRepresentation = data;
            });

            $scope.getTitle = function(){
                return ($scope.textRepresentation === 'Transliteration')? $scope.englishTransliteration : $scope.englishTranslation;
            }

            $scope.getSecondTitle = function(){
                return ($scope.textRepresentation === 'Transliteration')? $scope.englishTranslation : $scope.englishTransliteration;
            }

            $scope.multipleSingers = false;
            $scope.showDetails = function(){
                $scope.shouldShowDetails = true;
                if($scope.singers.length > 1)
                  $scope.multipleSingers = true;
            }

            $scope.hideDetails = function(){
                $scope.shouldShowDetails = false;
                $scope.multipleSingers = false;
            }

            $scope.hideDetails();
        }
    }
});