'use strict';

contentModule.directive("songWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imgSrc:'@',
            customStyle:'@',
        },
        templateUrl:'js/templates/content/song.html',
    }
});