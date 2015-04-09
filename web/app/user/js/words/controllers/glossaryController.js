angular.module("word").controller('glossaryController', ['$scope', 'wordService', function ($scope, wordService) {

    $scope.init = function () {
        wordService.getGlossaryPageContent().then(function (result) {
            $scope.glossary = result.data.words;
            $scope.numberOfTerms = $scope.glossary.length;
        });
    };

    $scope.init();
}]);
