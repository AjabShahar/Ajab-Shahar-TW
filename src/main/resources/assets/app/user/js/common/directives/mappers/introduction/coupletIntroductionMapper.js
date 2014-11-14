'use strict';

thumbnailModule.directive("coupletIntroductionMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'=',
            index:'@'
        },
        templateUrl:'/user/js/common/templates/mappers/introduction/coupletIntroductionMapper.html',
        controller:function($scope){
            $scope.couplet = {"id":"couplet_"+$scope.details.id, "originalText":$scope.details.originalText, "englishTranslationText":$scope.details.englishTranslationText,
                             "englishTransliterationText":$scope.details.englishTransliterationText, "poet":nameService.getName($scope.details.poet)
                        };
            $scope.init = function(){
                return $scope.showDetailsService.init($scope.couplet.id);
            }
            $scope.next = function(){
                return $scope.showDetailsService.next($scope.couplet.id);
            }
            $scope.prev = function(){
                return $scope.showDetailsService.prev($scope.couplet.id);
            }
            $scope.shouldShow = function(){
                return $scope.showDetailsService.shouldShow($scope.couplet.id);
            }
            $scope.close = function(){
                return $scope.showDetailsService.onClose($scope.couplet.id);
            }
            $scope.isClosed = function(){
                return $scope.showDetailsService.isClosed($scope.couplet.id);
            }
        }
    }
});