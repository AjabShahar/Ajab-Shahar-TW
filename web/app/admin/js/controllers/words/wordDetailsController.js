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
        $scope.synonyms = [];
        $scope.relatedWords = [];

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
            var wordsSummaryPromise = contentService.getAllWordsSummaries();

            $q.all([categoriesPromise, songsPromise, peoplePromise, reflectionsPromise, poetsPromise, wordsSummaryPromise]).then(function (data) {
                $scope.categories = data[0].data;
                $scope.songs = data[1].data.songs;
                $scope.writers = angular.copy(data[2].data.people);
                $scope.people = angular.copy(data[2].data.people);
                $scope.reflections = data[3].data.reflections;
                $scope.poets = data[4].data.people;
                $scope.synonyms = angular.copy(data[5].data.words);
                $scope.relatedWords = angular.copy(data[5].data.words);

                createMenuTitleForSongs();

                getWordDetails();
            });
        };

        $scope.saveData = function () {
            if($scope.wordForm.$valid){
                    contentService.saveWord($scope.formInfo).success(function () {
                    $window.location.href = '/admin/partials/home.html';
                });
            }
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
                    $scope.writers = getSelectedContent(data.writers, $scope.writers);
                    $scope.people = getSelectedContent(data.people, $scope.people);
                    $scope.songs = getSelectedContent(data.songs, $scope.songs);
                    $scope.reflections = getSelectedContent(data.reflections, $scope.reflections);
                    $scope.synonyms = removeElement(getSelectedContent(data.synonyms, $scope.synonyms), data);
                    $scope.relatedWords = removeElement(getSelectedContent(data.relatedWords, $scope.relatedWords), data);
                    $scope.reflectionsWithoutTheDefault=getSelectedContent(data.reflections, $scope.reflections);
                    $scope.formInfo = data;
                });
            }
        };
        var removeElement = function (list, element) {
            list.forEach(function (l) {

                if (l.id === element.id)
                    list.splice(list.indexOf(l), 1);
            });
            return list;
        };

        var redirectToURL = function (url) {
            $window.location.href = url;
        };

        $scope.redirectToEnterPage = function () {
            redirectToURL(PAGES.ADMIN_HOME);
        };

        $scope.isReflectionRequired = function(){
            var required = ($scope.formInfo.isRootWord === 'true' || $scope.formInfo.isRootWord === true) && (_.isEmpty($scope.formInfo.wordIntroductions) && _.isEmpty($scope.wordIntroductions));
            return required;
        };
        $scope.$watch("formInfo.defaultReflection",function(newValue){
            $scope.reflectionsWithoutTheDefault = _.reject($scope.reflections,function(reflection){
                return !_.isEmpty(newValue) && reflection.id === newValue.id;
            })
        })
    }]);

