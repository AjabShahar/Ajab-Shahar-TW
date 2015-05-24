"use strict";

featuredContentApp.controller('featuredContentController', ['$scope', 'mainLandingContentService', 'popupService', 'overviewMapperService',function ($scope, contentService, popupService, overviewMapperService) {
    $scope.thumbnailOpen = false;
    popupService.reset();

    $scope.thumbnails = {};
    $scope.featureContentOverviews = {};

    $scope.shiftThumbnail = function (index) {
        return "shift" + (index+1);
    };

    var thumbnailPlacementRule = {
        songs :[0,2,4,6,8],
        words:[3],
        reflections:[1,5,7]
    };

    $scope.init = function () {
        //var wordMapper = mappers.getWordMapper(),
        //    songMapper = mappers.getSongMapper(),
        //    reflectionMapper = mappers.getReflectionMapper(),
        var content = contentService.getMainLandingPageThumbnails();


        content.songs.then(function (result) {
            var songs = _.shuffle(result.data.songs).slice(0, 5);
            var introductions = overviewMapperService.toSongOverviews(songs);

            _.each(songs, function (song,index) {
                $scope.thumbnails[thumbnailPlacementRule.songs[index]] =new AjabShahar.ThumbnailObject(song,"song");
            });

            _.each(introductions, function (introduction,index) {
                popupService.addItem(introduction,thumbnailPlacementRule.songs[index]);
            });
        });

        content.words.then(function (result) {
            var words = _.shuffle(result.data.words).slice(0, 1);
            var introductions = overviewMapperService.toWordOverviews(words);

            _.each(words, function (word,index) {
                $scope.thumbnails[thumbnailPlacementRule.words[index]] = new AjabShahar.ThumbnailObject(word,"word");
            });

            _.each(introductions, function (introduction,index) {
                popupService.addItem(introduction,thumbnailPlacementRule.words[index]);
            });
        });

        content.reflections.then(function (result) {
            var reflections = _.shuffle(result.data.reflections).slice(0, 3);
            var introductions = overviewMapperService.toReflectionOverviews(reflections);

            _.each(reflections, function (reflection,index) {
                $scope.thumbnails[thumbnailPlacementRule.reflections[index]] = new AjabShahar.ThumbnailObject(reflection,"reflection");
            });

            _.each(introductions, function (introduction,index) {
                popupService.addItem(introduction,thumbnailPlacementRule.reflections[index]);
            });
        });

    }();

    $scope.selectThumbnail = function (index) {
        popupService.select(index);
    };

    $scope.selectedOverview = function(){
        return popupService.getSelected();
    }
}]);

