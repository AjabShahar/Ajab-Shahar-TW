'use strict';

popupSupport.directive("popupMapper", function() {
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
            $scope.$on('popupSelectionChanged', function(event,data){
                return $scope.showDetailsService.select(parseInt($scope.index),data);
            });
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