var songListController = function($scope, contentService,nameService){
    $scope.songs = [];
    $scope.nameService = nameService;
    $scope.init = function(){
        contentService.getAllSongs().then(function(result){
            $scope.songs = result.data;
        });
    }

    $scope.init();
    $scope.getName=function(person){
        return $scope.nameService.getName(person);
    }
}

adminApp.controller('songListController',['$scope','contentService','nameService',songListController]);