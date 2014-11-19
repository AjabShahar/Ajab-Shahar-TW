var carouselModule = angular.module('carouselModule', ['ngAnimate']);

carouselModule.directive('carousel', function($timeout) {
  return {
    restrict: 'E',
    transclude: true,
    scope: {},
    controller: ['$scope',function($scope) {
        $scope.currentIndex = 0; // Initially the index is at the first element
        $scope.sections = [];
        $scope.direction = '';

        $scope.prevSlide = function () {
             $scope.direction = 'left';
             $scope.currentIndex = ($scope.currentIndex < $scope.sections.length - 1) ? ++$scope.currentIndex : 0;
         };

        $scope.nextSlide = function () {
             $scope.direction = 'right';
             $scope.currentIndex = ($scope.currentIndex > 0) ? --$scope.currentIndex : $scope.sections.length - 1;
         };

        this.getCurrentIndex = function(){
            return $scope.currentIndex;
        }

        this.addSection = function(section) {
            $scope.sections.push(section);
         }
    }],
    link: function(scope, elem, attrs) {
    },
    templateUrl:'/user/js/common/templates/carouselSupport/carousel.html',
  };
})
.directive('section', function() {
     return {
       require: '^carousel',
       restrict: 'E',
       transclude: true,
       scope: { id: '@',index:'@' },
       link: function(scope, element, attrs, carouselCtrl) {
         carouselCtrl.addSection(scope);

         scope.isCurrentSlideIndex = function (index) {
              return carouselCtrl.getCurrentIndex() === index;
          };

       },
       templateUrl:'/user/js/common/templates/carouselSupport/section.html',
       replace: true
     };
   });