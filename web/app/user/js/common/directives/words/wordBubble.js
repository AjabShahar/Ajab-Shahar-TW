'use strict';

thumbnailModule.directive("wordBubble", function() {
    return {
        restrict: 'E',
        scope: {
            id:'@',
            wordTransliteration:'@',
            wordTranslation:'@',
            transliterationIntro:'@'
        },
        templateUrl:'/user/js/common/templates/words/wordBubble.html',
        controller: function($scope) {
        }
    }
});
