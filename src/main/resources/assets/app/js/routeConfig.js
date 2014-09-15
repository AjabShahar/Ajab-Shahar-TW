'use strict';

define('RouteConfig', function() {
  var routes = function($routeProvider) {
    $routeProvider.
    when('/phones', {
      templateUrl: 'partials/list.html',
      controller: 'ListController'
    }).
    when('/phones/:phoneId', {
      templateUrl: 'partials/profile.html',
      controller: 'ProfileController'
    }).
    otherwise({
      redirectTo: '/phones'
    });
  };
  
  return {
    routes: routes
  };
});
