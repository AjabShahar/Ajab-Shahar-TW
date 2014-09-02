'use strict';
module.exports = function (config) {
  config.set({
    basePath: '../../..',
    files: [
      'js/lib/underscore/underscore-min.js',
      'js/lib/angular/angular.js',
      'js/lib/angular-mocks/angular-mocks.js',
      'js/app/**/*.js',
      'test/js/unit/**/*.js'
    ],
    autoWatch: true,
    frameworks: ['jasmine'],
    browsers: ['PhantomJS'],
    reporters: ['dots', 'junit', 'coverage'],
    preprocessors: {
      'js/app/**/*.js': ['coverage']
    },
    logLevel: config.LOG_INFO,
    singleRun: true,
    junitReporter: {
      outputFile: './test-results.xml'
    },
    coverageReporter: {
      threshold: 85,
      reporters: [
        {
          type: 'cobertura',
          dir: 'coverage/'
        },
        {
          type: 'text-summary'
        }
      ]
    }
  });
};
