'use strict';

songsAdminApp.directive("asAdminTitle", function() {
    return {
        restrict: 'E',
        scope: {
            songTitle:'=',
            titleList:'=',
        },
        templateUrl:'/admin/js/templates/asAdminTitle.html',
        controller:function($scope){
            $scope.AddNewSongTitle = false;

            $scope.enableNewSongTitle = function(){
                $scope.songTitle ={"selected":null};
                return $scope.AddNewSongTitle = true;
            }

            $scope.disableNewSongTitle = function(){
                return $scope.AddNewSongTitle = false;
            }

        }
    }
});