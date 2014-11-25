'use strict';

popupSupport.directive("popupMapper", function(nameService) {
    return {
        restrict: 'E',
        transclude: true,
        scope: {
            showDetailsService:'=',
            totalCount:'@',
            id:'@',
            index:'@'
        },
        templateUrl:'/user/js/common/templates/popupSupport/popupMapper.html',
        controller:function($scope){
            $scope.init = function(){
                return $scope.showDetailsService.init($scope.id);
            }
            $scope.select = function(){
                return $scope.showDetailsService.select($scope.index);
            }
            $scope.shouldShow = function(){
                return $scope.showDetailsService.shouldShow($scope.id);
            }
            $scope.close = function(){
                return $scope.showDetailsService.onClose($scope.id);
            }
            $scope.isClosed = function(){
                return $scope.showDetailsService.isClosed($scope.id);
            }
        }
    }
});