'use strict';

thumbnailModule.directive("songContentDetailsMapper", function() {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            details:'=',
            detailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/songContentDetailsMapper.html',
        controller:function($scope){

        }
    }
});