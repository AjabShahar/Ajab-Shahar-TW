'use strict';
angular.module("word").controller('wordFeaturedContentController', ['$scope', 'wordService', 'sortService', function ($scope, wordService, sortService) {
    $scope.words = [];
    $scope.init = function () {
        var contentTextRepresentation = 'Transliteration';
        wordService.getWords().then(function (result) {
            var words = result.data.words;
            $scope.words = _.filter(words, function (word) {
                return word.isRootWord;
            });
            $scope.numberOfWords = $scope.words.length;
            $scope.words = sortService.sortList($scope.words,contentTextRepresentation);
        });

        $scope.$watch("contentTextRepresentation", function (value) {
            contentTextRepresentation = value;
            $scope.words = sortService.sortList($scope.words, contentTextRepresentation);
        });
    };

    $scope.setPageHeight = function () {
        var homepageContent = jQuery('.' + 'homepage-content')[0];
        var pxLayers = jQuery('.' + 'pxLayers');

        _.each(pxLayers, function (pxLayer) {
            pxLayer.style.height = (homepageContent.offsetHeight + 400) + "px";
        });
        homepageContent.style.height = (homepageContent.offsetHeight + 300) + "px";
    };
    $scope.init();
}]);