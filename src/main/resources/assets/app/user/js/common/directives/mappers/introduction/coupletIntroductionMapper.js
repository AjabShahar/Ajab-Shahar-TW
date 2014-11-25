'use strict';

thumbnailModule.directive("coupletIntroductionMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'=',
            index:'@',
            totalCount:'@'
        },
        templateUrl:'/user/js/common/templates/mappers/introduction/coupletIntroductionMapper.html',
        controller:function($scope){
            $scope.couplet = {"id":"couplet_"+$scope.details.id, "originalText":$scope.details.originalText, "englishTranslationText":$scope.details.englishTranslationText,
                             "englishTransliterationText":$scope.details.englishTransliterationText, "poet":nameService.getName($scope.details.poet)
                        };
        }
    }
});