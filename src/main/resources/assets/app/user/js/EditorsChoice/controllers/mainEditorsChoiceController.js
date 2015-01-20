var mainEditorsChoiceController = function($scope,contentService,popupService, mappers){
    $scope.detailsService = popupService;

    $scope.songThumbnails = [];
    $scope.songIntroductions = [];

    $scope.wordThumbnails = [];
    $scope.wordIntroductions = [];

    $scope.init = function(){
        var wordMapper = mappers.getWordMapper(),
            songMapper = mappers.getSongMapper(),
            content = contentService.getMainLandingPageThumbnails();

        content.songs.then(function(result){
            $scope.songThumbnails = songMapper.getThumbnails(result.data.songs, $scope.shiftThumbnailBy);
            $scope.introductions = songMapper.getIntroductions(result.data.songs);
        });

        content.words.then(function(result){
            $scope.wordThumbnails = wordMapper.getThumbnails(result.data, $scope.shiftThumbnailBy);
            $scope.wordIntroductions = wordMapper.getIntroductions(result.data);
        });
    }();

    $scope.shiftThumbnailBy = function(thumbnails, thumbnail){
        var index = _.indexOf(thumbnails, thumbnail);
        index = index + 1;
        return "shift"+index;
    }

    $scope.open = function(id){
        $scope.detailsService.open(id);
    }
}

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService','mappers',mainEditorsChoiceController]);