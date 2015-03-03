var genreListController = function($scope, genreContentService){
    $scope.genres = [];
    $scope.init = function(){
        genreContentService.getAllGenres().then(function(result){
            $scope.genres = result.data;
        });
    };

    $scope.init();
};

genresAdminApp.controller('genreListController',['$scope','genreContentService',genreListController]);