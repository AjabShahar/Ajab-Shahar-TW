var carouselModule = angular.module('carouselModule', ['ngAnimate']);

carouselModule.directive('carousel', function($timeout) {
  return {
    restrict: 'AE',
    replace: true,
    scope: {
      elements: '='
    },
    link: function(scope, elem, attrs) {
	scope.currentIndex = 0; // Initially the index is at the first element

	scope.next = function() {
        var oldIndex = scope.currentIndex;
        scope.currentIndex < scope.elements.length - 1 ? scope.currentIndex++ : scope.currentIndex = 0;
        scope.elements[oldIndex].visible = false;
        scope.elements[scope.currentIndex].visible= true;
	};

	scope.prev = function() {
        var oldIndex = scope.currentIndex;
        scope.currentIndex > 0 ? scope.currentIndex-- : scope.currentIndex = scope.elements.length - 1;
        scope.elements[oldIndex].visible = false;
        scope.elements[scope.currentIndex].visible= true;
	};
    },
    templateUrl:'/js/common/templates/carouselSupport/carousel.html',
  };
});