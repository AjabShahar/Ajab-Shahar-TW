"use strict";

angular.module("song").controller("songExploreController", ['$scope', '$route', 'songsContentService','$window',function ($scope, $route,songsContentService,$window) {

    var songId = null;
    if($route.current){
        songId = $route.current.params.songId;
    }
    $scope.thumbnails = [];
    $scope.format = "transliteration";
    $scope.selectThumbnail = function(thumbnail){
        $window.location.href = thumbnail.getUrl();
    };

    $scope.currentSelection = "";

    $scope.applyFilter = function(contentType){
        $scope.currentSelection = contentType;
    };

    $scope.clearFilters = function(){
        $scope.currentSelection = "";
    };

    var relatedWordIntros = [];
    var relatedReflections =[];
    var relatedSongs =[];

     $scope.contentAvailable = function(type){
        switch (type){
            case 'song': return relatedSongs.length>0;
            case 'word': return relatedWordIntros.length>0;
            case 'reflection': return relatedReflections.length>0;
        }
        return false;
    };

    $scope.getDetailsPageUrl = function(){
        return "/songs/details?id="+songId;
    };

    $scope.init = function () {

        songsContentService.getSong(songId).then(function (result) {
            $scope.song = result.data;
            if(!_.isEmpty($scope.song)){
                relatedWordIntros = AjabShahar.SongExploreHelper.createWordThumbnails($scope.song.words);
                relatedReflections = AjabShahar.SongExploreHelper.createReflectionThumbnails($scope.song.reflections);
                $scope.thumbnails = $scope.thumbnails.concat(relatedReflections,relatedWordIntros);

                songsContentService.getRelatedContentFromRelatedWordsOf($scope.song).success(function(response){
                    var wordReflections =AjabShahar.SongExploreHelper.getReflectionsFromWordReflections(response,relatedReflections);
                    relatedReflections = relatedReflections.concat(wordReflections);
                    $scope.thumbnails = $scope.thumbnails.concat(wordReflections);

                    var currentSong = {id: parseInt(songId)};
                    relatedSongs =AjabShahar.SongExploreHelper.getSongsFromWordReflections(response,[currentSong]);
                    $scope.thumbnails = $scope.thumbnails.concat(relatedSongs);
                });
            }
        });
    };

    if(songId){
        $scope.init();
    }

}]);
