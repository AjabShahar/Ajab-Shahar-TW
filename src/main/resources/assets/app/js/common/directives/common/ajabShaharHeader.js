'use strict';

thumbnailModule.directive("ajabShaharHeader", function() {
    return {
        restrict: 'E',
        scope: {
            shouldExpandByDefault:'@'
        },
        templateUrl:'/js/common/templates/common/header.html',
        controller:function($scope){
        }
    }
});