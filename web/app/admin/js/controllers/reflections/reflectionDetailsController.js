'use strict';
reflectionsAdminApp.controller('reflectionDetailsController', ['$scope', '$window', '$location', 'reflectionContentService', "loginVerifyService", "$q", '$filter',
    function ($scope, $window, $location, reflectionContentService, loginVerifyService, $q, $filter) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.reflection = {"reflectionTranscripts": [{}], "speaker": {}};
        $scope.peopleList = [];
        $scope.people = [];
        $scope.words = [];
        $scope.songs = [];

        var getSelectedContent = function (data, list) {
            return angular.forEach(list, function (item) {
                angular.forEach(data, function (selectedItem) {
                    if (!item.ticked) {
                        item.ticked = (selectedItem.id === item.id);
                    }
                });
            });
        };

        var sortList = function (list, sortCriteria) {
            return $filter('orderBy')(list, sortCriteria);
        };

        var createMenuTitleForSongs = function () {
            angular.forEach($scope.songs, function (song) {
                var singerNames = _.pluck(song.singers, 'name');
                if (_.isEmpty(singerNames)) {
                    song.menuTitle = song.englishTransliterationTitle;
                } else {
                    song.menuTitle = song.englishTransliterationTitle + " - (" + singerNames.join(", ") + ")";
                }
            });

            $scope.songs = sortList($scope.songs, 'menuTitle');
        };

        var REFLECTION_TYPE = {
            audio: "audio",
            video: "video",
            text: "text"
        };

        var resetOtherTypeRelatedFields = function(type){
            if(type == REFLECTION_TYPE.audio){
                $scope.reflection.youtubeVideoId = null;
                $scope.reflection.reflectionTranscripts = null;
                $scope.reflection.info = null;
            }
            else if(type == REFLECTION_TYPE.video){
                $scope.reflection.soundCloudId = null;
                $scope.reflection.reflectionTranscripts = null;
                $scope.reflection.info = null;
            }
            else {
                $scope.reflection.soundCloudId = null;
                $scope.reflection.youtubeVideoId = null;
                $scope.reflection.about = null;
            }
        };

        var init = function () {
            var getPeoplePromise = reflectionContentService.getPeople();

            var getWordsPromise = reflectionContentService.getWords();

            var getSongsPromise = reflectionContentService.getSongs();


            $q.all([getPeoplePromise, getWordsPromise, getSongsPromise]).then(function (data) {
                $scope.people = sortList(data[0].data, 'name');
                var urlId = $location.search().id;
                $scope.songs = data[2].data.songs;
                $scope.words = sortList(data[1].data, 'wordTransliteration');
                if (urlId != null && urlId != '') {
                    reflectionContentService.getRefectionById(urlId).success(function (data) {
                        $scope.reflection = data;
                        $scope.reflection.reflectionTranscripts = $scope.reflection.reflectionTranscripts || [{}];
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
            if ($scope.reflection.thumbnailURL && $scope.reflection.thumbnailURL.indexOf("http") === -1 && $scope.reflection.thumbnailURL.indexOf("/images/") === -1) {
                $scope.reflection.thumbnailURL = '/images/' + $scope.reflection.thumbnailURL;
            }

            resetOtherTypeRelatedFields($scope.reflection.type);

            reflectionContentService.saveReflection($scope.reflection).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        var getReflectionType = function () {
            return ($scope.reflection.soundCloudId != null ? 'audio' : ($scope.reflection.youtubeVideoId != null ? 'video' : (!$scope.reflection.about ? 'text' : 'video')));
        };

        init();
    }]);

