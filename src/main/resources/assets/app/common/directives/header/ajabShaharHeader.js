'use strict';

headerModule.directive("ajabShaharHeader", function($window) {
    return {
        restrict: 'E',
        scope: {
            showMenuContent:'@',
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
                $scope.showMenuContent = !$scope.showMenuContent;
            }

            $scope.shouldShowMenuContent = function(){
                return $scope.showMenuContent;
            }

        }
    }
});