'use strict';

thumbnailModule.directive("songIntroduction", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            url:'@'
        },
        templateUrl:'/js/common/templates/songs/introduction.html',
        controller:function($scope){
        }
    }
});