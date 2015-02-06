var featuredContentController = function($scope,contentService,popupService, mappers){
    $scope.detailsService = popupService;
    $scope.thumbnailOpen = false;

    $scope.songThumbnails = [];
    $scope.songIntroductions = [];

    $scope.wordThumbnails = [];
    $scope.wordIntroductions = [];

    $scope.reflectionThumbnails = [];

    var index = 0;
    var shiftThumbnail = function(){
        ++index;
        return "shift"+index;
    }

    $scope.init = function(){
        var wordMapper = mappers.getWordMapper(),
            songMapper = mappers.getSongMapper(),
            reflectionMapper = mappers.getReflectionMapper(),
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

        content.reflections.then(function(result){
           var reflections = _.shuffle(result.data.reflections).slice(0,1);
           $scope.reflectionThumbnails = reflectionMapper.getThumbnails(reflections,shiftThumbnail);

        })
    }();

    $scope.open = function(id){
        $scope.detailsService.open(id);
    }
}

featuredContentApp.controller('featuredContentController',['$scope','contentService','popupService','mappers',featuredContentController]);
