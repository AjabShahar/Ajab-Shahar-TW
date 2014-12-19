'use strict';

songsAdminApp.directive("asAdminTitle", function() {
    return {
        restrict: 'E',
        scope: {
            title:'@',
            titleId:'=',
            titleData:'=',
            titleList:'=',
        },
        templateUrl:'/admin/js/templates/asAdminTitle.html',
        controller:function($scope){
            $scope.AddNewTitle = false;
            $scope.EditDiv = false;

            $scope.enableNewSongTitle = function(){
                $scope.titleData ={"selected":null};
                $scope.AddNewTitle = true;
            }

            $scope.disableNewSongTitle = function(){
                $scope.titleData ={"selected":null};
                $scope.EditDiv = false;
                $scope.AddNewTitle = false;
            }

            $scope.$watch('titleId',function(newValue,oldValue){
                if(newValue==oldValue)
                    return;
                $scope.titleData = _.findWhere($scope.titleList,{"id":$scope.titleId});
            });
        }
    }
});