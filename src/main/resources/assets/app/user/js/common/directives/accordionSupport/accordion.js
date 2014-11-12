var accordionModule = angular.module('accordionModule', ['ngAnimate']);

accordionModule.directive('accordion', function($timeout) {
  return {
    restrict: 'E',
    transclude: true,
    scope: {},
    controller: ['$scope',function($scope) {
        $scope.expandAccordion = true;

        $scope.toggleAccordionDisplay = function(){
            $scope.expandAccordion = !$scope.expandAccordion;
        }

        $scope.getClazz = function(){
            return ($scope.expandAccordion)? "out" : "in";

        }
    }],
    link: function(scope, elem, attrs) {
    },
    templateUrl:'/user/js/common/templates/accordionSupport/accordion.html',
  };
});