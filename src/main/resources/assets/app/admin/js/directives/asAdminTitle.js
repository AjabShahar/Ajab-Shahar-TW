'use strict';

songsAdminApp.directive("asAdminTitle", function() {
    return {
        restrict: 'E',
        scope: {
            title:'@',
            titleData:'=',
            titleList:'=',
        },
        templateUrl:'/admin/js/templates/asAdminTitle.html',
        controller:function($scope){
            $scope.AddNewTitle = false;

            $scope.enableNewSongTitle = function(){
                $scope.titleData ={"selected":null};
                $scope.AddNewTitle = true;
            }

            $scope.disableNewSongTitle = function(){
                $scope.titleData ={"selected":null};
                $scope.EditDiv = false;
                $scope.AddNewTitle = false;
            }

        }
    }
});