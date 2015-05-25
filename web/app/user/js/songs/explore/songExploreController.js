"use strict";

angular.module("song").controller("songExploreController", ['$scope', '$route', 'songsContentService',function ($scope, $route,songsContentService) {

    var songId = null;
    if($route.current){
        songId = $route.current.params.songId;
    }
    $scope.thumbnails = [];

    $scope.init = function () {
        var relatedWordIntros = [];
        var relatedReflections =[];
        songsContentService.getSong(songId).then(function (result) {
            $scope.song = result.data;
            if(!_.isEmpty($scope.song)){
                relatedWordIntros = AjabShahar.SongExploreHelper.createWordThumbnails($scope.song.words);
                relatedReflections = AjabShahar.SongExploreHelper.createReflectionThumbnails($scope.song.reflections);
                $scope.thumbnails = $scope.thumbnails.concat(relatedWordIntros,relatedReflections);

                songsContentService.getReflectionsFromRelatedWordsOf($scope.song).success(function(response){
                    var wordReflections =AjabShahar.SongExploreHelper.getReflectionsFromWordReflections(response);
                    $scope.thumbnails = $scope.thumbnails.concat(wordReflections);
                });

                songsContentService.getSongsFromRelatedWordsOf($scope.song).success(function(response){
                    var wordSongs =AjabShahar.SongExploreHelper.getSongsFromWordReflections(response);
                    $scope.thumbnails = $scope.thumbnails.concat(wordSongs);
                });
            }
        });
    };

    if(songId){
        $scope.init();
    }
}]);
