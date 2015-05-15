adminApp.controller('songListController', ['$scope', 'contentService', 'loginVerifyService',
    function ($scope, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.songs = [];
        $scope.songToBeDeleted = null;

        $scope.init = function () {
            contentService.getAllSongs().then(function (result) {
                var allSongs = result.data.songs;
                $scope.songs = _.reduce(allSongs, function (songs, value, index) {
                    var toBeAdded = {};
                    toBeAdded.title = value.englishTransliterationTitle;
                    toBeAdded.translatedTitle = value.englishTranslationTitle;
                    toBeAdded.categoryName = value.category;
                    toBeAdded.publish = value.publish;

                    if (value.publish)
                        toBeAdded.publish = "Yes";

                    else
                        toBeAdded.publish = "No";

                    toBeAdded.singerNames = _.reduce(value.singers, function (memo, singer, index) {
                        return (memo + ((index != 0) ? ', ' : '') + singer.name);
                    }, '');
                    toBeAdded.poetNames = _.reduce(value.poet, function (memo, poet, index) {
                        return (memo + ((index != 0) ? ', ' : '') + poet.name);
                    }, '');
                    toBeAdded.id = value.id;
                    songs.push(toBeAdded);
                    return songs;
                }, []);
            });
        };

        $scope.init();
    }]);
