angular.module("word").controller('glossaryController', ['$scope', 'wordService',  '$filter','$rootScope', function ($scope, wordService,$filter,$rootScope) {

    $rootScope.pageSynopsis = {
        title:"Glossary"
    };
    var sortList = function (list, sortCriteria) {
        return $filter('orderBy')(list, sortCriteria);
    };

    $scope.init = function () {
        wordService.getGlossaryPageContent().then(function (result) {
            $scope.glossary = sortList(result.data.words, 'wordTransliteration');

            $scope.glossary = _.filter($scope.glossary, function(value){
                return value.meaning != null && value.meaning != "";
            });

            $scope.numberOfTerms = $scope.glossary.length;
        });
    };

    $scope.getWordUrl = function(word){
        return word?AjabShahar.user.ContentUrlCreator.getUrl(word,"word"):"";
    };
    $scope.classes = ['envelope-letter-bg', 'yellow-trapezium-bg', 'chakras-bg','benzene-bg','ameoba-bg'];

    $scope.init();
}]);
