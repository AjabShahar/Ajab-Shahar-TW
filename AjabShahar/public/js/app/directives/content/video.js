'use strict';

contentModule.directive("videoWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            videoid: '@',
            name:'@',
            singer:'@',
            showcontrols:'@',
        },
        templateUrl:'js/templates/content/video.html',
    }
});