'use strict';
define('controllers/ProfileController', function() {

  var profileController = function($scope, $http, $routeParams, ratingService) {
    $scope.phone = {};
    $http.get('data/' + $routeParams.phoneId + '.json').success(function(data) {
      $scope.phone = data;
      $scope.phone.rating = ratingService.getRating($scope.phone.id);
    });
  };
  return profileController;
});
