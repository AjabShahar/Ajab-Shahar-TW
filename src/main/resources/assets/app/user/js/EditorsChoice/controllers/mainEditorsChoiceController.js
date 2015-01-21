var mainEditorsChoiceController = function($scope,contentService,popupService, mappers){
    $scope.detailsService = popupService;

    $scope.songThumbnails = [];
    $scope.songIntroductions = [];

    $scope.wordThumbnails = [];
    $scope.wordIntroductions = [];

    var index = 0;
    var shiftThumbnail = function(){
        ++index;
        return "shift"+index;
    }

    $scope.init = function(){
        var wordMapper = mappers.getWordMapper(),
            songMapper = mappers.getSongMapper(),
            content = contentService.getMainLandingPageThumbnails();

        content.songs.then(function(result){
            $scope.songThumbnails = songMapper.getThumbnails(result.data.songs, shiftThumbnail);
            $scope.introductions = songMapper.getIntroductions(result.data.songs);

            content.words.then(function(result){
                $scope.wordThumbnails = wordMapper.getThumbnails(result.data, shiftThumbnail);
                $scope.wordIntroductions = wordMapper.getIntroductions(result.data);
            });
        });
    }();

    $scope.open = function(id){
        $scope.detailsService.open(id);
    }
}

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService','mappers',mainEditorsChoiceController]);