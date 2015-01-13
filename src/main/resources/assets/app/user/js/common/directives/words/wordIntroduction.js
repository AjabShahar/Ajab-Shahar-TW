'use strict';

thumbnailModule.directive("wordIntroduction", function() {
    return {
        restrict: 'E',
        scope: {
            id:'@',
            wordOrPhrase:'@',
            originalIntro:'@',
            translationIntro:'@',
            transliterationIntro:'@',
            showWordIntro:'@'
        },
        templateUrl:'/user/js/common/templates/words/wordIntroduction.html',
        controller: function($scope) {
        }
    }
});