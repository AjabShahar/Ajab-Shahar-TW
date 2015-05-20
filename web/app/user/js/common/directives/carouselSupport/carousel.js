'use strict';

thumbnailModule.directive("carousel", function () {
    return {
        restrict: 'E',
        templateUrl:"/user/js/common/directives/carouselSupport/carousel.html",
        scope: {
            items:"=",
            itemClicked:"=",
            format:"="
        },
        link: function (scope, elem) {
            var carousel = {};
            scope.jcarousel = function(){
                $(elem).find('.jcarousel').jcarousel();
                carousel= $(elem).find('.jcarousel').data('jcarousel');

            };

            scope.showPrev = function(){
                carousel.scroll('-=1');
            };

            scope.showNext = function(){
                carousel.scroll('+=1');
            };

            scope.selectThumbnail = function(index){
                if(carousel && carousel.scroll) carousel.scroll(index);
                scope.selectedThumbnail=scope.items[index];
                scope.itemClicked(scope.items[index])
            };

            scope.$watch('items',function(){
                scope.selectThumbnail(0);
            });
        }
    }
});