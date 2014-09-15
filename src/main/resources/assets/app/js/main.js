'use strict';

require.config({
  paths: {
    angular: '../bower_components/angular/angular',
    angularRoute: '../bower_components/angular-route/angular-route',
  },
  shim: {
    'angular': {
      'exports': 'angular'
    },
    'angularRoute': ['angular']
  }
});

require(['angular', 'angularRoute','app'],
  function(angular) {
   var appRoot = document.getElementById('ngtut');
    angular.element(appRoot).ready(function () {
        angular.bootstrap(document, ['ngtut']);
    });
  });
