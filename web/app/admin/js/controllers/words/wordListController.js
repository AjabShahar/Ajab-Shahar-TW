adminApp.controller('wordListController', ['$scope', 'contentService', 'loginVerifyService',
    function ($scope, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.words = [];
        $scope.init = function () {
            contentService.getAllWordsSummaries().then(function (result) {
                var allWords = result.data.words;
                $scope.words = _.reduce(allWords, function (words, value, index) {
                    var toBeAdded = {};
                    toBeAdded.wordOrPhrase = value.wordTransliteration;
                    toBeAdded.meaning = value.wordTranslation;
                    toBeAdded.id = value.id;
                    words.push(toBeAdded);
                    return words;
                }, []);
            });
        };

        $scope.init();
    }]);

