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
            elem.jcarousel();

            var carousel = elem.data('jcarousel');
            scope.showPrev = function(){
                carousel.scroll('-=1');
            };

            scope.showNext = function(){
                carousel.scroll('+=1');
            };

            scope.selectThumbnail = function(index){
                carousel.scroll(index);
                scope.selectedThumbnail=scope.items[index];
                scope.itemClicked(scope.items[index])
            };

            scope.$watch('items',function(){
                scope.selectThumbnail(0);
            });
        }
    }
});