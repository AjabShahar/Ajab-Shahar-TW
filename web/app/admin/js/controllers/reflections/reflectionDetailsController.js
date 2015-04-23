'use strict';
reflectionsAdminApp.controller('reflectionDetailsController', ['$scope', '$window', '$location', 'reflectionContentService', "loginVerifyService","$q",
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

        var init = function () {
            var getPeoplePromise = reflectionContentService.getPeople();

            var getWordsPromise = reflectionContentService.getWords();

            var getSongsPromise = reflectionContentService.getSongs();

            if (urlId != null && urlId != '') {
                var getReflectionPromise = reflectionContentService.getRefectionById(urlId);

                $q.all([getPeoplePromise, getWordsPromise, getSongsPromise, getReflectionPromise]).then(function (data) {
                    $scope.people = data[0].data;
                    $scope.reflection = data[3].data;
                    $scope.words = getSelectedContent($scope.reflection.words, data[1].data.words);
                    $scope.songs = getSelectedContent($scope.reflection.songs, data[2].data.songs);
                    $scope.people = getSelectedContent($scope.reflection.people, data[0].data);

                    $scope.reflection.type = getReflectionType();
                });
            }
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

