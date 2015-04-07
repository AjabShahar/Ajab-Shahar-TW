var songsController = function ($scope, $location) {
    $location.url($location.absUrl());
    $scope.songId = $location.search().id;
    $scope.songDetails = false;
    $scope.allSongs = false;

    if (Boolean($scope.songId)) {
        $scope.songDetails = true;
        $location.path('/details');
    }
    else {
        $scope.allSongs = true;
        $location.path('/allSongs');
    }
};

songsApp.controller('songsController', ['$scope', '$location', songsController]);
