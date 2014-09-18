'use strict';

contentModule.directive("songWithDetails", function() {
    return {
        restrict: 'E',
        scope: {
            name:'@',
            singer:'@',
            imgSrc:'@',
            customStyle:'@',
            lightBoxImageUrl:'@',
        },
        controller: function($scope) {
            $scope.showLightBox = function(){

            }
        },
        link: function($scope,element, attrs){
//                    $(".group2").colorbox({iframe:true, innerWidth:640, innerHeight:390});
            var colorBoxConfig = {rel:'group1', arrowKey: false};
            $(".group1").colorbox(colorBoxConfig);
        },
        templateUrl:'js/templates/content/song.html',
    }
});