songsAdminApp.controller('songDetailsController', ['$scope', '$window', '$location', 'songContentService', 'PAGES', '$filter', '$q', "loginVerifyService",
    function ($scope, $window, $location, songContentService, PAGES, $filter, $q, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.song = {
            singers: [],
            poets: [],
            songText: {},
            isAuthoringComplete: false,
            mediaCategory: {},
            words: [],
            songCategory: ""
        };
        $scope.singers = [];
        $scope.poets = [];
        $scope.songCategories = [];
        $scope.mediaCategories = [];
        $scope.umbrellaTitles = [];
        $scope.songTitles = [];
        $scope.genres = [];
        $scope.words = [];
        $scope.gatherings = [];
        $scope.reflections = [];

        var sortList = function (list, sortCriteria) {
            return $filter('orderBy')(list, sortCriteria);
        };

        var removeNulls = function (personList) {
            var persons = angular.forEach(personList, function (person) {
                person.lastName = (Boolean(person.lastName)) ? person.lastName : '';
            });
            return sortList(persons, 'name');
        };

        $scope.isEmpty = function (value) {
            return !Boolean(value);
        };

        $scope.init = function () {
            var genrePromise = songContentService.getGenres();
            var titlePromise = songContentService.getSongTitles();
            var songCategoryPromise = songContentService.getSongCategories();
            var mediaCategoryPromise = songContentService.getMediaCategories();
            var umbrellaTitlePromise = songContentService.getUmbrellaTitles();
            var singerPromise = songContentService.getSingers();
            var poetPromise = songContentService.getPoets();
            var wordPromise = songContentService.getWords();
            var gatheringPromise = songContentService.getGatherings();
            var reflectionsPromise = songContentService.getReflections();

            var promises = [genrePromise, titlePromise, songCategoryPromise, mediaCategoryPromise, umbrellaTitlePromise, singerPromise, poetPromise, wordPromise, gatheringPromise, reflectionsPromise];

            $q.all(promises).then(function (data) {
                $scope.genres = sortList(data[0].data,'english');
                $scope.songTitles = sortList(data[1].data,'englishTransliteration');
                $scope.songCategories = data[2].data;
                $scope.mediaCategories = data[3].data;
                $scope.umbrellaTitles = sortList(data[4].data,'englishTransliteration');
                $scope.singers = removeNulls(data[5].data);
                $scope.poets = removeNulls(data[6].data);
                $scope.words = sortList(data[7].data.words,'wordTransliteration');
                $scope.gatherings = sortList(data[8].data, 'english');
                $scope.reflections = sortList(data[9].data.reflections, 'title');
                $scope.song.songCategory = $scope.songCategories[0];

                $scope.getSongData();
            });
        };

        var setMediaCategory = function () {
            if (Boolean($scope.song.youtubeVideoId)) {
                $scope.song["mediaCategory"] = $scope.mediaCategories.filter(function (mediaCategory) {
                    return mediaCategory.name == "audio & video";
                })[0];
            }
            else {
                $scope.song["mediaCategory"] = $scope.mediaCategories.filter(function (mediaCategory) {
                    return mediaCategory.name == "audio only";
                })[0];
            }
        };

        var redirectToURL = function (url) {
            $window.location.href = url;
        };

        $scope.saveData = function () {
            setMediaCategory();

            if ($scope.song.thumbnailURL && $scope.song.thumbnailURL.indexOf("http") === -1 && $scope.song.thumbnailURL.indexOf("/images/") === -1) {
                $scope.song.thumbnailURL = '/images/' + $scope.song.thumbnailURL;
            }
            songContentService.createSong($scope.song)
                .error(function (data) {
                    alert(data);
                })
                .success(function () {
                    redirectToURL(PAGES.ADMIN_HOME);
                });
        };

        var getSelectedContent = function (data, list) {
            return angular.forEach(list, function (item) {
                angular.forEach(data, function (selectedItem) {
                    if (!item.ticked) {
                        item.ticked = (selectedItem.id === item.id);
                    }
                });
            });
        };

        $scope.getSongData = function () {
            var songId = $location.search().id;

            songContentService.getSong(songId).success(function (data) {
                $scope.genres = getSelectedContent(data.songGenre, $scope.genres);
                $scope.singers = getSelectedContent(data.singers, $scope.singers);
                $scope.words = getSelectedContent(data.words, $scope.words);
                $scope.reflections = getSelectedContent(data.reflections, $scope.reflections);
                $scope.song = data;

                if ($scope.song.songText) {
                    $scope.song.songText.songTextContents = sortList($scope.song.songText.songTextContents, 'sequenceNumber');
                } else {
                    $scope.song.songText = {
                        songTextContents: [],
                        openingCouplets: []
                    }
                }
            });
        };

        $scope.isMediaUrlEmpty = function () {
            return !(Boolean($scope.song.youtubeVideoId) || Boolean($scope.song.soundCloudTrackId));
        };

        $scope.updateSong = function () {
            setMediaCategory();
            $scope.song.publishedDate = null;

            if ($scope.song.thumbnailURL && $scope.song.thumbnailURL.indexOf("http") === -1 && $scope.song.thumbnailURL.indexOf("/images/") === -1) {
                $scope.song.thumbnailURL = '/images/' + $scope.song.thumbnailURL;
            }

            songContentService.editSong($scope.song)
                .error(function (data) {
                    alert(data);
                })
                .success(function () {
                    redirectToURL(PAGES.ADMIN_HOME);
                });
        };
    }]);


