'use strict';

contentModule.directive("imageWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            showcontrols:'@',
            imgSrc:'@'
        },
        templateUrl:'js/templates/content/image.html',
    }
});