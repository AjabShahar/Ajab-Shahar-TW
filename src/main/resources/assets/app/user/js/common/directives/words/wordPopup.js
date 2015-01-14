'use strict';

thumbnailModule.directive("wordPopup", function() {
    return {
        restrict: 'E',
        scope: {
            id:'@',
            wordOrPhraseTransliteration:'@',
            wordOrPhraseTranslation:'@',
            originalIntro:'@',
            translationIntro:'@',
            transliterationIntro:'@',
            showWordIntro:'@'
        },
        templateUrl:'/user/js/common/templates/words/wordPopup.html',
        controller: function($scope) {
        }
    }
});