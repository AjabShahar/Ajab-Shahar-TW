'use strict';

define('app', ['angular', 'RouteConfig',
    'directives/RatingDirective', 'services/RatingService',
    'controllers/ListController', 'controllers/ProfileController', 'controllers/ItemController'
  ],
  
  function(angular, config,
    ratingDirective, ratingService,
    listController, profileController, itemController) {

    var ngtut = angular.module('ngtut', ['ngRoute']);
    ngtut.config(['$routeProvider', config.routes]);

    ngtut.directive('rating', ratingDirective);

    ngtut.factory('ratingService', ratingService);

    ngtut.controller('ListController', ['$scope', '$http', listController]);
    ngtut.controller('ItemController', ['$scope', 'ratingService', itemController]);
    ngtut.controller('ProfileController', ['$scope', '$http', '$routeParams', 'ratingService', profileController]);

    return ngtut;
  });
