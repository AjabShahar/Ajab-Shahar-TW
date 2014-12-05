'use strict';

adminApp.directive("lyrics", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            lyricsComponent:'=',
            originalText:'=',
        },
        templateUrl:'/admin/js/templates/lyrics.html',
        controller:function($scope){
        }
    }
});