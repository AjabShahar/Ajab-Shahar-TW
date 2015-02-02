var featuredContentController = function($scope,contentService,popupService, mappers){
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
            var songs = _.shuffle(result.data.songs).slice(0,4);
            $scope.songThumbnails = songMapper.getThumbnails(songs, shiftThumbnail);
            $scope.introductions = songMapper.getIntroductions(songs);

            content.words.then(function(result){
                var words = _.shuffle(result.data.words).slice(0,4);
                $scope.wordThumbnails = wordMapper.getThumbnails(words, shiftThumbnail);
                $scope.wordIntroductions = wordMapper.getIntroductions(words);
            });
        });
    }();

    $scope.open = function(id){
        $scope.detailsService.open(id);
    }
}

featuredContentApp.controller('featuredContentController',['$scope','contentService','popupService','mappers',featuredContentController]);
