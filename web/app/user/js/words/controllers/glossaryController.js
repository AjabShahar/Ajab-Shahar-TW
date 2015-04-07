var glossaryController = function ($scope, contentService) {

    $scope.init = function () {
        contentService.getGlossaryPageContent().then(function (result) {
            $scope.glossary = result.data.words;
            $scope.numberOfTerms = $scope.glossary.length;
        });
    };

    $scope.init();
};

glossaryApp.controller('glossaryController', ['$scope', 'contentService', glossaryController]);
