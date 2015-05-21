var songDetailsController = function ($scope, $location, songsContentService, songMapper) {

    var carouselOpen = true;

    $scope.init = function () {
        $scope.carouselItems = [];
        $scope.songId = $location.search().id;
        songsContentService.getSongsVersions($scope.songId).then(function (result) {
            $scope.carouselItems = $scope.carouselItems.concat(
                result.data.songs.map(function (song) {
                    return new AjabShahar.ThumbnailObject(song, "song");
                })
            );
            $scope.numberOfVersions = $scope.carouselItems.length;
        });
    };

    $scope.containsVersions = function () {
        return $scope.numberOfVersions > 1;
    };

    $scope.shouldShowCarousel = function () {
        return carouselOpen && $scope.containsVersions()
    };

    $scope.toggleCarousel = function () {
        carouselOpen = !carouselOpen;
    };

    $scope.getSongsLyrics = function (songTextComponents, openingCouplets) {
        $scope.songText.songTextContents = [];
        $scope.songText.openingCouplets = [];
        sortedSongTextComponents = _.sortBy(songTextComponents, function (songTextComponent) {
            return songTextComponent.sequenceNumber;
        });
        for (index in sortedSongTextComponents) {
            var item = sortedSongTextComponents[index];
            $scope.songText.songTextContents.push(item);
        }

        sortedSongTextComponents = _.sortBy(openingCouplets, function (openingCouplet) {
            return openingCouplet.sequenceNumber;
        });
        for (index in sortedSongTextComponents) {
            var item = sortedSongTextComponents[index];
            $scope.songText.openingCouplets.push(item);
        }
    };

    $scope.selectThumbnail = function (thumbnail) {
        songsContentService.getSong(thumbnail.id).success(function (response) {
            $scope.detailsObject = new AjabShahar.DetailsObject(response, thumbnail.type)
        });
    };


    $scope.showEnglishTranslation = function () {
        $scope.englishTransliterationVisible = false;
        $scope.englishTranslationVisible = true;
        $scope.originalVisible = false;
    };

    $scope.showEnglishTransliteration = function () {
        $scope.englishTransliterationVisible = true;
        $scope.englishTranslationVisible = false;
        $scope.originalVisible = false;
    };

    $scope.showOriginal = function () {
        $scope.englishTransliterationVisible = false;
        $scope.englishTranslationVisible = false;
        $scope.originalVisible = true;
    };

    $scope.getCustomStyle = function (song) {
        if (Boolean(song)) {
            if (song.id == $scope.songId)
                return 'active';
            return '';
        }
        return '';
    };
    $scope.init();
};

songDetailsApp
    .controller('songDetailsController', ['$scope', '$location', 'songsContentService', 'songMapper', songDetailsController]);
