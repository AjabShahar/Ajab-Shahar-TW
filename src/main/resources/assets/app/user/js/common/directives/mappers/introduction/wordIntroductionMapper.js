'use strict';

thumbnailModule.directive("wordIntroductionMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'=',
            index:'@',
            totalCount:'@'
        },
        templateUrl:'/user/js/common/templates/mappers/introduction/wordIntroductionMapper.html',
        controller:function($scope){
            $scope.word = {"id":"word_"+$scope.details.id, "englishTranslation":$scope.details.wordOrPhrase,
                        };
            $scope.init = function(){
                return $scope.showDetailsService.init($scope.word.id);
            }
            $scope.next = function(){
                return $scope.showDetailsService.next($scope.word.id);
            }
            $scope.prev = function(){
                return $scope.showDetailsService.prev($scope.word.id);
            }
            $scope.shouldShow = function(){
                return $scope.showDetailsService.shouldShow($scope.word.id);
            }
            $scope.close = function(){
                return $scope.showDetailsService.onClose($scope.word.id);
            }
            $scope.isClosed = function(){
                return $scope.showDetailsService.isClosed($scope.word.id);
            }
        }
    }
});