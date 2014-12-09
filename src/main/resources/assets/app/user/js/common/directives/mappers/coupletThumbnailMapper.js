'use strict';

thumbnailModule.directive("coupletThumbnailMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/coupletThumbnailMapper.html',
        controller:function($scope){
            $scope.couplet = {"id":"couplet_"+$scope.details.id, "customStyle":$scope.customStyle,"imgSrc":$scope.details.thumbnail_url,"englishTranslationText":$scope.details.englishTranslationText.slice(0,30),
                            "title":$scope.details.englishTransliteration,
                            "categoryName":$scope.details.category.name
                        };
            $scope.open = function(){
                return $scope.showDetailsService.open($scope.couplet.id);
            }
        }
    }
});