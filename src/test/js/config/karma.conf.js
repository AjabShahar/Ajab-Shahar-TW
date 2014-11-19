'use strict';
module.exports = function (config) {
  config.set({
    basePath: '../../../main/resources/assets/app',
    files: [
      'common/lib/jquery/dist/jquery.min.js',
      'common/lib/underscore/underscore-min.js',
      'common/lib/angular/angular.js',
      'common/lib/angular-mocks/angular-mocks.js',
      'common/services/nameService.js',
      'common/modules/headerModule.js',
      'user/js/common/modules/thumbnailModule.js',
      'user/js/common/modules/PopupSupport.js',
      'user/js/common/modules/mediaPlayer.js',
      'user/js/common/**/*.js',
      'user/js/Introduction/**/*.js',
      'user/js/LandingPages/**/*.js',
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
