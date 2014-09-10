'use strict';

angular.module('angular-parallax', [
]).directive('parallax', ['$window', function($window) {
  return {
    restrict: 'A',
    scope: {
      parallaxRatio: '@',
      parallaxVerticalOffset: '@',
      parallaxHorizontalOffset: '@',
    },
    link: function($scope, elem, $attrs) {
      var setPosition = function () {
        // horizontal positioning
        elem.css('left', $scope.parallaxHorizontalOffset + "px");

        var calcValY = $window.pageYOffset * ($scope.parallaxRatio ? $scope.parallaxRatio : 1.1 );
        if (calcValY <= $window.innerHeight) {
          var topVal = (calcValY < $scope.parallaxVerticalOffset ? $scope.parallaxVerticalOffset : calcValY);
          elem.css('top', topVal + "px");
        }
      };

      setPosition();

      angular.element($window).bind("scroll", setPosition);
      angular.element($window).bind("touchmove", setPosition);
    }  // link function
  };
}]).directive('parallaxBackground', ['$window', function($window) {
  return {
    restrict: 'A',
    transclude: true,
    template: '<div ng-transclude></div>',
    scope: {
      parallaxRatio: '@',
    },
    link: function($scope, elem, attrs) {
      var setPosition = function () {
        var calcValY = (elem.prop('offsetTop') - $window.pageYOffset) * ($scope.parallaxRatio ? $scope.parallaxRatio : 1.1 );
        // horizontal positioning
        elem.css('background-position', "50% " + calcValY + "px");
      };

      // set our initial position - fixes webkit background render bug
      angular.element($window).bind('load', function(e) {
        setPosition();
        $scope.$apply();
      });

      angular.element($window).bind("scroll", setPosition);
      angular.element($window).bind("touchmove", setPosition);
    }  // link function
  };
}]).directive("scroll", function ($window) {
    var lastScrollTop = 0;
    var newMarginValue = 0;
    var moveCloudsToLeft = function(margin){
        var margin = margin.substring(0, margin.length - 2);
        newMarginValue = margin - 12;
        document.getElementById("clouds").style.marginLeft = newMarginValue + "px";
    }

    var moveCloudsToRight = function(margin){
        var margin = margin.substring(0, margin.length - 2);
        newMarginValue = parseInt(margin) + 12;
        document.getElementById("clouds").style.marginLeft = newMarginValue + "px";
    }

  return function(element, attrs) {
      angular.element($window).bind("scroll", function() {
        var currentScrollPosition = $window.scrollY;
        var isDownwordScroll = currentScrollPosition > lastScrollTop;
        var element = document.getElementById('clouds'),
          style = window.getComputedStyle(element),
          marginLeftValue = style.getPropertyValue('margin-left').toString();

        if(isDownwordScroll){
          moveCloudsToLeft(marginLeftValue);
        }
        else{
          moveCloudsToRight(marginLeftValue);
        }
        lastScrollTop = currentScrollPosition;
      });
  };
});
;
