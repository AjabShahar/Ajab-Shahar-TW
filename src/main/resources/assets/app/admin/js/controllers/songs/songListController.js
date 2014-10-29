var songDetailsController = function($scope, contentService,nameService){
    $scope.songs = [];
    $scope.nameService = nameService;
    $scope.init = function(){
        contentService.getAllSongs().then(function(result){
            $scope.songs = result.data;
        });
    }

    $scope.init();
}

adminApp.controller('songDetailsController',['$scope','contentService','nameService',songDetailsController]);