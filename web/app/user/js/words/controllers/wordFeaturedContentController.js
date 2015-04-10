'use strict';
angular.module("word").controller('wordFeaturedContentController', ['$scope', 'wordService', function ($scope, wordService) {
    $scope.init = function () {
        wordService.getWordsLandingPageContent().then(function (result) {
            var words = result.data.words;
            $scope.words = _.filter(words, function (word) {
                return word.isRootWord;
            });
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