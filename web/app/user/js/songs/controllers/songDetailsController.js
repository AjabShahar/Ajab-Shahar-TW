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

    $scope.selectThumbnail = function (thumbnail) {
        if(thumbnail != null){
            songsContentService.getSong(thumbnail.id).success(function (response) {
                $scope.detailsObject = new AjabShahar.DetailsObject(response, thumbnail.type);
                $scope.selectedSong = response || {};
                $scope.hasSongExploreContent = false;

                initialiseMainTitles();
                hasExploreContent();
            });
        }
    };

    $scope.hasSongText = function () {
        return !_.isEmpty($scope.selectedSong.songText) && (!_.isEmpty($scope.selectedSong.songText.original) || !_.isEmpty($scope.selectedSong.songText.translation) || !_.isEmpty($scope.selectedSong.songText.transliteration));
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

    $scope.getSongExploreUrl = function(){
        return !_.isEmpty($scope.detailsObject) &&$scope.hasSongExploreContent  ? "/songs/explore/"+$scope.detailsObject.id:'';
    };

    var hasExploreContent = function(){

            _.each($scope.selectedSong.reflections,function(reflection){
                if(reflection.published)
                    $scope.hasSongExploreContent = true;
            });

             _.each($scope.selectedSong.words,function(word){
                if(word.publish)
                    $scope.hasSongExploreContent = true;
            });
    }
};

songDetailsApp
    .controller('songDetailsController', ['$scope', '$location', 'songsContentService', songDetailsController]);
