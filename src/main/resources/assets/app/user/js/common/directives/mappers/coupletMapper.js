'use strict';

thumbnailModule.directive("coupletMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/coupletMapper.html',
        controller:function($scope){
            $scope.couplet = {"id":"couplet_"+$scope.details.id, "customStyle":$scope.customStyle,"imgSrc":$scope.details.thumbnail_url,"englishTranslationText":$scope.details.englishTranslationText,
                            "title":$scope.details.englishTranslation,
                            "categoryName":$scope.details.category.name
                        };
            $scope.open = function(){
                return $scope.showDetailsService.open($scope.song.id);
            }
        }
    }
});