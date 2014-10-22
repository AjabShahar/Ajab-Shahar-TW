'use strict';
module.exports = function (config) {
  config.set({
    basePath: '../../../main/resources/assets/app',
    files: [
      'lib/jquery/dist/jquery.min.js',
      'lib/underscore/underscore-min.js',
      'lib/angular/angular.js',
      'lib/angular-mocks/angular-mocks.js',
      'js/common/directives/thumbnail/thumbnailModule.js',
      'js/common/**/*.js',
      'js/Introduction/**/*.js',
      'js/LandingPages/services/htmlGenerator.js',
      'js/LandingPages/services/coupletThumbnailService.js',
      'js/LandingPages/services/filmThumbnailService.js',
      'js/LandingPages/services/gatheringThumbnailService.js',
      'js/LandingPages/**/*.js',
      '../../../../test/js/unit/**/*.js'
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
