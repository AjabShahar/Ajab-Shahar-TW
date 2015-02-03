var songsController = function($scope,$location){
    $location.url($location.absUrl());
    $scope.songId = $location.search().id;

    if(Boolean($scope.songId))
        $location.path('/details');
    else
        $location.path('/allSongs');
};

songsApp.controller('songsController',['$scope','$location',songsController]);