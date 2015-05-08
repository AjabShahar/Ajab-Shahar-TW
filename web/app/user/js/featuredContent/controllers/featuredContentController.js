var featuredContentController = function ($scope, contentService, popupService, mappers) {
    $scope.detailsService = popupService;
    $scope.thumbnailOpen = false;

    $scope.thumbnails = {};
    $scope.featureContentOverviews = {};

    $scope.shiftThumbnail = function (index) {
        return "shift" + (index+1);
    };

    $scope.format = "Transliteration";

    var thumbnailPlacementRule = {
        songs :[0,2,4,6,8],
        words:[3],
        reflections:[1,5,7]
    };

    $scope.init = function () {
        var wordMapper = mappers.getWordMapper(),
            songMapper = mappers.getSongMapper(),
            reflectionMapper = mappers.getReflectionMapper(),
            content = contentService.getMainLandingPageThumbnails();


        content.songs.then(function (result) {
            var songs = _.shuffle(result.data.songs).slice(0, 5);
            var introductions = songMapper.getOverviews(songs);

            _.each(songs, function (song,index) {
                $scope.thumbnails[thumbnailPlacementRule.songs[index]] =new AjabShahar.ThumbnailObject(song,"song");
            });

            _.each(introductions, function (introduction,index) {
                $scope.featureContentOverviews[thumbnailPlacementRule.songs[index]] = introduction;
            });
        });

        content.words.then(function (result) {
            var words = _.shuffle(result.data.words).slice(0, 1);
            var introductions = wordMapper.getOverviews(words);

            _.each(words, function (word,index) {
                $scope.thumbnails[thumbnailPlacementRule.words[index]] = new AjabShahar.ThumbnailObject(word,"word");
            });

            _.each(introductions, function (introduction,index) {
                $scope.featureContentOverviews[thumbnailPlacementRule.words[index]] = introduction;
            });
        });

        content.reflections.then(function (result) {
            var reflections = _.shuffle(result.data.reflections).slice(0, 3);
            var introductions = reflectionMapper.getOverviews(reflections);

            _.each(reflections, function (reflection,index) {
                $scope.thumbnails[thumbnailPlacementRule.reflections[index]] = new AjabShahar.ThumbnailObject(reflection,"reflection");
            });

            _.each(introductions, function (introduction,index) {
                $scope.featureContentOverviews[thumbnailPlacementRule.reflections[index]] = introduction;
            });
        });

    }();

    $scope.selectThumbnail = function (index) {
        $scope.detailsService.open($scope.thumbnails[index].type+"_"+$scope.thumbnails[index].id);
    };

    $scope.popupCount = function(){
        return _.keys($scope.featureContentOverviews).length;
    }
};

featuredContentApp.controller('featuredContentController', ['$scope', 'mainLandingContentService', 'popupService', 'mappers', featuredContentController]);
