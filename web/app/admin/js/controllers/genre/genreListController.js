var genreListController = genresAdminApp.controller('genreListController', ['$scope', 'genreContentService', "loginVerifyService",
    function ($scope, genreContentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.genres = [];
        $scope.init = function () {
            genreContentService.getAllGenres().then(function (result) {
                $scope.genres = result.data;
            });
        };

        $scope.init();
    }]);

