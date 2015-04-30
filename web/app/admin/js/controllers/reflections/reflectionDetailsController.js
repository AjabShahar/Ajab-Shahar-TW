'use strict';
reflectionsAdminApp.controller('reflectionDetailsController', ['$scope', '$window', '$location', 'reflectionContentService', "loginVerifyService", "$q",
    function ($scope, $window, $location, reflectionContentService, loginVerifyService, $q) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.reflection = {"reflectionTranscripts": [], "speaker": {}};
        $scope.peopleList = [];
        $scope.people = [];
        $scope.words = [];
        $scope.songs = [];
        var urlId = $location.search().id;

        var getSelectedContent = function (data, list) {
            return angular.forEach(list, function (item) {
                angular.forEach(data, function (selectedItem) {
                    if (!item.ticked) {
                        item.ticked = (selectedItem.id === item.id);
                    }
                });
            });
        };

        var createMenuTitleForSongs = function () {
            angular.forEach($scope.songs, function (song) {
                var singerNames = _.pluck(song.singers, 'name');
                if (_.isEmpty(singerNames)) {
                    song.menuTitle = song.songTitle.englishTransliteration;
                } else {
                    song.menuTitle = song.songTitle.englishTransliteration + " - (" + singerNames.join(", ") + ")";
                }
            });
        };

        var init = function () {
            var getPeoplePromise = reflectionContentService.getPeople();

            var getWordsPromise = reflectionContentService.getWords();

            var getSongsPromise = reflectionContentService.getSongs();

            $q.all([getPeoplePromise, getWordsPromise, getSongsPromise]).then(function (data) {
                $scope.people = data[0].data;
                $scope.songs = data[2].data.songs;
                $scope.words = data[1].data;
                if (urlId != null && urlId != '') {
                    reflectionContentService.getRefectionById(urlId).success(function (data) {
                        $scope.reflection = data;
                        $scope.words = getSelectedContent($scope.reflection.words, $scope.words);
                        $scope.songs = getSelectedContent($scope.reflection.songs, $scope.songs);
                        $scope.people = getSelectedContent($scope.reflection.people, $scope.people);
                        $scope.reflection.type = getReflectionType();
                    });
                }
                createMenuTitleForSongs();
            });
        };

        $scope.saveData = function () {
            reflectionContentService.saveReflection($scope.reflection).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        var getReflectionType = function () {
            return ($scope.reflection.soundCloudId != null ? 'audio' : ($scope.reflection.youtubeVideoId != null ? 'video' : ($scope.reflection.reflectionTranscripts.length > 0 ? 'text' : '')));
        };

        init();
    }]);

