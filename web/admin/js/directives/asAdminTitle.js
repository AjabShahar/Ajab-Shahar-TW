'use strict';

songsAdminApp.directive("asAdminTitle", function() {
    return {
        restrict: 'E',
        scope: {
            title:'@',
            titleId:'=',
            titleData:'=',
            titleList:'=',
            validationRequired: '@',
        },
        templateUrl:'/admin-js/templates/asAdminTitle.html',
        controller:function($scope){
            $scope.AddNewTitle = false;
            $scope.EditDiv = false;
            $scope.oldTitle = {};

            $scope.validate = function(value) {
                return $scope.validationRequired && !Boolean(value);
            }

            $scope.enableNewSongTitle = function(){
                $scope.oldTitle = angular.copy($scope.titleData);
                $scope.titleData ={"selected":null};
                $scope.AddNewTitle = true;
            };

            $scope.disableNewSongTitle = function(){
                $scope.EditDiv = false;
                if($scope.AddNewTitle)
                  $scope.titleData = angular.copy($scope.oldTitle);
                $scope.AddNewTitle = false;
            };

            $scope.$watch('titleId',function(newValue,oldValue){
                if(newValue==oldValue)
                    return;
                $scope.titleData = _.findWhere($scope.titleList,{"id":$scope.titleId});
            });
        }
    };
});
