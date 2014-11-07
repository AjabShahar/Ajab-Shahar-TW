'use strict';

headerModule.directive("ajabShaharHeader", function($window) {
    return {
        restrict: 'E',
        scope: {
            showMenuDetails:'@',
            callBack:'&',
        },
        templateUrl:'/common/js/templates/header/ajabShaharHeader.html',
        controller:function($scope){
            $window.onload = function () {
                $scope.callBack && $scope.callBack();
            }
            $scope.getElementsByClassName = function(className){
                return $window.document.getElementsByClassName(className)[0];
            }

            $scope.showMenu = function(){
                $scope.showMenuDetails = true;
                $scope.$digest();
            }

            $scope.hideMenu = function(){
                $scope.showMenuDetails = false;
                $scope.$digest();
            }

            $scope.toggleMenu = function(){
                $scope.showMenuDetails = !$scope.showMenuDetails;
            }
        }
    }
});