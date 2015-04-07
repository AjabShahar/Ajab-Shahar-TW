var featuredContentController = function ($scope, contentService, popupService, mappers) {
    $scope.detailsService = popupService;
    $scope.thumbnailOpen = false;

    $scope.thumbnails = [];
    $scope.featureContentOverviews = [];

    var index = 0;
    var shiftThumbnail = function () {
        ++index;
        return "shift" + index;
    };

    $scope.init = function () {
        var wordMapper = mappers.getWordMapper(),
            songMapper = mappers.getSongMapper(),
            reflectionMapper = mappers.getReflectionMapper(),
            content = contentService.getMainLandingPageThumbnails();

        content.songs.then(function (result) {
            var songs = _.shuffle(result.data.songs).slice(0, 4);
            var songThumbnails = songMapper.getThumbnails(songs, shiftThumbnail);
            var introductions = songMapper.getOverviews(songs);

            _.each(songThumbnails, function (thumbnail) {
                $scope.thumbnails.push(thumbnail);
            });

            _.each(introductions, function (introduction) {
                $scope.featureContentOverviews.push(introduction);
            });
        });

        content.words.then(function (result) {
            var words = _.shuffle(result.data.words).slice(0, 4);
            var wordThumbnails = wordMapper.getThumbnails(words, shiftThumbnail);
            var introductions = wordMapper.getOverviews(words);

            _.each(wordThumbnails, function (thumbnail) {
                $scope.thumbnails.push(thumbnail);
            });

            _.each(introductions, function (introduction) {
                $scope.featureContentOverviews.push(introduction);
            });
        });

        content.reflections.then(function (result) {
            var reflections = _.shuffle(result.data.reflections).slice(0, 1);
            var reflectionThumbnails = reflectionMapper.getThumbnails(reflections, shiftThumbnail);
            var introductions = reflectionMapper.getOverviews(reflections);

            _.each(reflectionThumbnails, function (thumbnail) {
                $scope.thumbnails.push(thumbnail);
            });

            _.each(introductions, function (introduction) {
                $scope.featureContentOverviews.push(introduction);
            });
        });

    }();

    $scope.open = function (id) {
        $scope.detailsService.open(id);
    }
};

featuredContentApp.controller('featuredContentController', ['$scope', 'contentService', 'popupService', 'mappers', featuredContentController]);
