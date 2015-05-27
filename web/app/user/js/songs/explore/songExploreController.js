"use strict";

angular.module("song").controller("songExploreController", ['$scope', '$route', 'songsContentService','$q',function ($scope, $route,songsContentService,$q) {

    var songId = null;
    if($route.current){
        songId = $route.current.params.songId;
    }
    $scope.thumbnails = [];
    $scope.format = "transliteration";
    $scope.selectThumbnail = function(){

    };

    $scope.currentSelection = "";

    $scope.applyFilter = function(contentType){
        $scope.currentSelection = contentType;
    };

    $scope.clearFilters = function(){
        $scope.currentSelection = "";
    };

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
                    var wordReflections =AjabShahar.SongExploreHelper.getReflectionsFromWordReflections(response,relatedReflections);
                    $scope.thumbnails = $scope.thumbnails.concat(wordReflections);
                });

                songsContentService.getSongsFromRelatedWordsOf($scope.song).success(function(response){
                    var currentSong = {id: parseInt(songId)};
                    var wordSongs =AjabShahar.SongExploreHelper.getSongsFromWordReflections(response,[currentSong]);
                    $scope.thumbnails = $scope.thumbnails.concat(wordSongs);
                });
            }
        });

    };


    if(songId){
        $scope.init();
    }

}]);
