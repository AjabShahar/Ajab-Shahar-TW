var mainLandingPageController = function(){
}

ajabShaharApp.controller('mainLandingPageController',[mainLandingPageController]);
var lastScrollTop = 0;

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

ajabShaharApp.directive("scroll", function ($window) {
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
