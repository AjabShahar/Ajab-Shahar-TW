'use strict';

contentModule.directive("imageWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imgSrc:'@',
            shiftDirection:'@',
            customStyle:'@',
        },
        templateUrl:'js/templates/content/song.html',
    }
});