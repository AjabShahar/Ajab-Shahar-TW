var mainEditorsChoiceController = function($scope,contentService,popupService, mappers){
    $scope.detailsService = popupService;

    $scope.songThumbnails = [];
    $scope.songIntroductions = [];

    $scope.wordThumbnails = [];
    $scope.wordIntroductions = [];

    $scope.init = function(){
        var content = contentService.getMainLandingPageThumbnails();
        
        content.songs.then(function(result){
            $scope.songThumbnails = mappers.getSongMapper().getThumbnails(result.data.songs,$scope.getCustomStyleForThumbnail);
            $scope.introductions = mappers.getSongMapper().getIntroductions(result.data.songs);

        });

        content.words.then(function(result){
            $scope.wordThumbnails = mappers.getWordMapper().getThumbnails(result.data, $scope.getCustomStyleForThumbnail);
            $scope.wordIntroductions = mappers.getWordMapper().getIntroductions(result.data);
        });
    }();

    $scope.getCustomStyleForThumbnail = function(thumbnails, thumbnail){
        var thumbnailId = _.indexOf(thumbnails, thumbnail);
        var index = thumbnailId + 1;
        return "shift"+index;
    }

    $scope.open = function(id){
        $scope.detailsService.open(id);
    }
}

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService','mappers',mainEditorsChoiceController]);