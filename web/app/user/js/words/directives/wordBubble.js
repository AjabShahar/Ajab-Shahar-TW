'use strict';

angular.module("word").directive("wordBubble", function () {
    return {
        restrict: 'E',
        scope: {
            id: '@',
            wordTransliteration: '@',
            wordTranslation: '@',
            transliterationIntro: '@'
        },
        templateUrl: '/user/js/words/directives/templates/wordBubble.html',
        controller: function ($scope) {
        }
    }
});
