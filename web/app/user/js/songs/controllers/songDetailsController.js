var songDetailsController = function ($scope, $location, songsContentService) {

    var carouselOpen = true;
    $scope.mainTitle = {transliteration: "", translation: ""};
    $scope.selectedSong = {};
    $scope.songText = {};
    $scope.englishTransliterationVisible = true;

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
            sortCarouselItems();
        });
    };

    var sortCarouselItems = function () {
        for (index in $scope.carouselItems) {
            if ($scope.carouselItems[index].id == $scope.songId) {
                var dummyVar = $scope.carouselItems[index];
                $scope.carouselItems.splice(index, 1);
                $scope.carouselItems.splice(0, 0, dummyVar);
            }
        }
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

    var getSongsLyrics = function (song) {
        $scope.songText.songTextContents = [];
        $scope.songText.openingCouplets = [];
        $scope.refrainOriginal = song.songText.refrainOriginal;
        $scope.refrainTranslation = song.songText.refrainEnglishTranslation;
        $scope.refrainTransliteration = song.songText.refrainEnglishTransliteration;


        sortedSongTextComponents = _.sortBy(song.songText.songTextContents, function (songTextComponent) {
            return songTextComponent.sequenceNumber;
        });
        for (index in sortedSongTextComponents) {
            var item = sortedSongTextComponents[index];
            $scope.songText.songTextContents.push(item);
        }

        sortedSongTextComponents = _.sortBy(song.songText.openingCouplets, function (openingCouplet) {
            return openingCouplet.sequenceNumber;
        });
        for (index in sortedSongTextComponents) {
            var item = sortedSongTextComponents[index];
            $scope.songText.openingCouplets.push(item);
        }
    };

    $scope.selectThumbnail = function (thumbnail) {
        if(thumbnail != null){
            songsContentService.getSong(thumbnail.id).success(function (response) {
                $scope.detailsObject = new AjabShahar.DetailsObject(response, thumbnail.type);
                $scope.selectedSong = response;

                initialiseMainTitles();
                getSongsLyrics($scope.selectedSong)

            });
        }
    };

    $scope.hasSongText = function () {
        if ($scope.selectedSong.songText != null && $scope.selectedSong.songText.songTextContents.length > 0)
            return true;
        return false;
    };

    var initialiseMainTitles = function () {
        if (!_.isEmpty($scope.selectedSong.umbrellaTitle.englishTranslation)) {
            $scope.mainTitle.translation = $scope.selectedSong.umbrellaTitle.englishTranslation;
            $scope.mainTitle.transliteration = $scope.selectedSong.umbrellaTitle.englishTransliteration;
        }
        else {
            $scope.mainTitle.translation = $scope.selectedSong.songTitle.englishTranslation;
            $scope.mainTitle.transliteration = $scope.selectedSong.songTitle.englishTransliteration;
        }
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
    .controller('songDetailsController', ['$scope', '$location', 'songsContentService', songDetailsController]);
