angular.module("word").controller('glossaryController', ['$scope', 'wordService',  '$filter', function ($scope, wordService,$filter) {

    var sortList = function (list, sortCriteria) {
        return $filter('orderBy')(list, sortCriteria);
    };

    $scope.init = function () {
        wordService.getGlossaryPageContent().then(function (result) {
            $scope.glossary = sortList(result.data.words, 'wordTransliteration');
            $scope.numberOfTerms = $scope.glossary.length;
        });
    };

    $scope.init();
}]);
