'use strict';

thumbnailModule.directive("wordThumbnail", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            name:'@',
            introductionSummary:'@',
            customStyle:'@',
            overlayId:'@',
            id:'@',
        },
        templateUrl:'/user-js/common/templates/thumbnail/wordThumbnail.html',
        controller:function($scope){
        }
    }
});