'use strict';
define('controllers/ItemController', function() {

  var itemController = function($scope, ratingService) {
    $scope.$watch('phone.rating', function(newValue, oldValue) {
      ratingService.setRating($scope.phone.id, newValue);
    });
  };
  return itemController;
});
