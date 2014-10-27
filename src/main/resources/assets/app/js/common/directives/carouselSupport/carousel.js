var carouselModule = angular.module('carouselModule', ['ngAnimate']);

carouselModule.directive('carousel', function($timeout) {
  return {
    restrict: 'E',
    transclude: true,
    replace: true,
    scope: {},
    controller: ['$scope',function($scope) {
      $scope.currentIndex = 0; // Initially the index is at the first element
      $scope.sections = [];
      $scope.carouselSelection = 'one';

      $scope.next = function() {
          var oldIndex = $scope.currentIndex;
          $scope.currentIndex < $scope.sections.length - 1 ? $scope.currentIndex++ : $scope.currentIndex = 0;
          $scope.sections[oldIndex].selected = false;
          $scope.sections[$scope.currentIndex].selected = true;
      };

      $scope.prev = function() {
          var oldIndex = $scope.currentIndex;
          $scope.currentIndex > 0 ? $scope.currentIndex-- : $scope.currentIndex = $scope.sections.length - 1;
          $scope.sections[oldIndex].selected = false;
          $scope.sections[$scope.currentIndex].selected = true;
      };

      this.addSection = function(section) {
        $scope.sections.push(section);
        if($scope.sections.length==1)
        {
            $scope.currentIndex = 0;
            $scope.sections[$scope.currentIndex].selected = true;
        }
      }
    }],
    link: function(scope, elem, attrs) {
    },
    templateUrl:'/js/common/templates/carouselSupport/carousel.html',
  };
})
.directive('section', function() {
     return {
       require: '^carousel',
       restrict: 'E',
       transclude: true,
       scope: { id: '@' },
       link: function(scope, element, attrs, carouselCtrl) {
         scope.selected = false;
         scope.isSelected = function(){
            return scope.selected;
         }

         carouselCtrl.addSection(scope);
       },
       template:
       '<div ng-init="selected=false" ng-show="isSelected()" class="carousel-animation" ng-transclude>'+
        '</div>',
       replace: true
     };
   });