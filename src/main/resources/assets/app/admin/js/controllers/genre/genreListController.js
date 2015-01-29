var genreListController = function($scope, contentService){
    $scope.genres = [];
    $scope.init = function(){
        contentService.getAllGenres().then(function(result){
            $scope.genres = result.data;
        });
    }

    $scope.init();
}

adminApp.controller('genreListController',['$scope','contentService',genreListController]);