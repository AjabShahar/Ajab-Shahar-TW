wordsAdminApp.controller('wordDetailsController', ['$scope', '$window', '$location', 'contentService', 'PAGES', '$q', "loginVerifyService",
    function ($scope, $window, $location, contentService, PAGES, $q, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();

        $scope.formInfo = {
            reflections: [],
            wordIntroductions: [],
            displayAjabShaharTeam: false
        };
        $scope.categories = [];
        $scope.reflections = [];
        $scope.writers = [];
        $scope.people = [];
        $scope.songs = [];
        $scope.poets = [];

        var wordCategory = 'word';

        var createMenuTitleForSongs = function () {
            angular.forEach($scope.songs, function (song) {
                var singerNames = _.pluck(song.singers, 'name');
                if (_.isEmpty(singerNames)) {
                    song.menuTitle = song.englishTransliterationTitle;
                } else {
                    song.menuTitle = song.englishTransliterationTitle + " - (" + singerNames.join(", ") + ")";
                }
                song.words = song.words.words;
            });
        };

        $scope.init = function () {
            var reflectionsPromise = contentService.getAllReflectionSummaries();
            var peoplePromise = contentService.getAllPeople();
            var categoriesPromise = contentService.getAllCategories(wordCategory);
            var songsPromise = contentService.getAllSongs();
            var poetsPromise = contentService.getPoets();

            $q.all([categoriesPromise, songsPromise, peoplePromise, reflectionsPromise, poetsPromise]).then(function (data) {
                $scope.categories = data[0].data;
                $scope.songs = data[1].data.songs;
                $scope.writers = angular.copy(data[2].data.people);
                $scope.people = angular.copy(data[2].data.people);
                $scope.reflections = data[3].data.reflections;
                $scope.poets = data[4].data.people;

                createMenuTitleForSongs();

                getWordDetails();
            });
        };

        $scope.saveData = function () {
            contentService.saveWord($scope.formInfo).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        var getSelectedContent = function (data, list) {
            return angular.forEach(list, function (item) {
                angular.forEach(data, function (selectedItem) {
                    if (!item.ticked) {
                        item.ticked = (selectedItem.id === item.id)
                    }
                });
            });
        };

        var getWordDetails = function () {
            var wordID = $location.search().id;

            if (wordID) {
                contentService.getWord(wordID).success(function (data) {
                    $scope.reflections = getSelectedContent(data.reflection, $scope.reflections);
                    $scope.writers = getSelectedContent(data.writers, $scope.writers);
                    $scope.people = getSelectedContent(data.people, $scope.people);
                    $scope.songs = getSelectedContent(data.songs, $scope.songs);
                    $scope.reflections = getSelectedContent(data.reflections, $scope.reflections);
                    $scope.formInfo = data;
                });
            }
        };

        var redirectToURL = function (url) {
            $window.location.href = url;
        };

        $scope.redirectToEnterPage = function () {
            redirectToURL(PAGES.ADMIN_HOME);
        };

        $scope.updateWord = function () {
            contentService.updateWord($scope.formInfo).success(function () {
                redirectToURL(PAGES.ADMIN_HOME);
            });
        };
    }]);

