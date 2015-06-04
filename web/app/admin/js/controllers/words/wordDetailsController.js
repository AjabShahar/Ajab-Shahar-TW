wordsAdminApp.controller('wordDetailsController', ['$scope', '$window', '$location', 'contentService', 'PAGES', '$q', "loginVerifyService", '$filter',
    function ($scope, $window, $location, contentService, PAGES, $q, loginVerifyService, $filter) {
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
            });
            $scope.songs = sortList($scope.songs, 'menuTitle');
        };

        var sortList = function (list, sortCriteria) {
            return $filter('orderBy')(list, sortCriteria);
        };

        $scope.init = function () {
            var reflectionsPromise = contentService.getAllReflectionSummaries();
            var peoplePromise = contentService.getAllPeopleSummary();
            var categoriesPromise = contentService.getAllCategories(wordCategory);
            var songsPromise = contentService.getAllSongs();
            var poetsPromise = contentService.getAllPoetsSummary();
            var wordsSummaryPromise = contentService.getAllWordsSummaries();

            $q.all([categoriesPromise, songsPromise, peoplePromise, reflectionsPromise, poetsPromise, wordsSummaryPromise]).then(function (data) {
                $scope.categories = data[0].data;
                $scope.songs = data[1].data.songs;
                $scope.writers = sortList(angular.copy(data[2].data),'name');
                $scope.people = sortList(angular.copy(data[2].data),'name');
                $scope.reflections = sortList(data[3].data.reflections,'title');
                $scope.reflectionsWithoutTheDefault = sortList(angular.copy($scope.reflections),'title');
                $scope.poets = sortList(data[4].data,'name');
                $scope.synonyms = sortList(angular.copy(data[5].data),'wordTransliteration');
                $scope.relatedWords = sortList(angular.copy(data[5].data),'wordTransliteration');

                createMenuTitleForSongs();

                getWordDetails();
            });
        };

        $scope.saveData = function () {
            if($scope.wordForm.$valid){
                if ($scope.formInfo.thumbnailUrl && $scope.formInfo.thumbnailUrl.indexOf("http") === -1 && $scope.formInfo.thumbnailUrl.indexOf("/images/") === -1) {
                    $scope.formInfo.thumbnailUrl = '/images/' + $scope.formInfo.thumbnailUrl;
                }
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
                    $scope.synonyms = getSelectedContent(data.synonyms, $scope.synonyms);
                    $scope.relatedWords = getSelectedContent(data.relatedWords, $scope.relatedWords);
                    $scope.reflectionsWithoutTheDefault=getSelectedContent(data.reflections, $scope.reflections);
                    $scope.formInfo = data;
                });

                //TODO: we have to refactor , this is duplication
                $scope.synonyms.forEach(function(synonum){
                    if(synonum.id === parseInt(wordID)){
                        $scope.synonyms.splice($scope.synonyms.indexOf(synonum),1);
                    }
                });

                $scope.relatedWords.forEach(function(relatedWord){
                   if(relatedWord.id === parseInt(wordID)){
                       $scope.relatedWords.splice($scope.relatedWords.indexOf(relatedWord),1);
                   }
                });
            }
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

