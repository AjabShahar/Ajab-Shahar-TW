'use strict';

thumbnailModule.directive("songIntroduction", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imageUrl:'@'
        },
        templateUrl:'/js/common/templates/songs/introduction.html',
        controller:function($scope){
        }
    }
});