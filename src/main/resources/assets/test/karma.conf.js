'use strict';

module.exports = function(config) {
  config.set({

    basePath: '../',
    // logLevel: config.LOG_DEBUG,
    files: [
      'app/bower_components/angular/angular.js',
      'app/bower_components/angular-route/angular-route.js',
      'app/bower_components/angular-mocks/angular-mocks.js',
      'app/js/**/*.js',
      'test/test-main.js',
      'test/unit/**/*.js'
    ],
    exclude: [
      'app/js/main.js'
    ],
    autoWatch: true,

    frameworks: ['jasmine', 'requirejs'],

    browsers: ['PhantomJS'],

    plugins: [
      'karma-chrome-launcher',
      'karma-phantomjs-launcher',
      'karma-jasmine',
      'karma-requirejs'
    ],

    junitReporter: {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }
  });
};
