'use strict';

thumbnailModule.directive("wordOverview", function() {
    return {
        restrict: 'E',
        scope: {
            id:'@',
            wordTransliteration:'@',
            wordTranslation:'@',
            wordIntroEnglish:'@',
        },
        templateUrl:'/user-js/common/templates/words/wordOverview.html',
        controller: function($scope) {
        }
    }
});