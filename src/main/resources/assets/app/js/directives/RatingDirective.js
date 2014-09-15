'use strict';

define('directives/RatingDirective', [], function() {
  var rating = function() {
    return {
      restrict: 'EA', 
      templateUrl: 'partials/rating.html',
      scope: false
    };
  };
  return rating;
});
