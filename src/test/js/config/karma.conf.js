'use strict';
module.exports = function (config) {
  config.set({
    basePath: '../../../main/resources/assets/app',
    files: [
      'common/lib/jquery/dist/jquery.min.js',
      'common/lib/underscore/underscore-min.js',
      'common/lib/angular/angular.js',
      'common/lib/angular-animate/angular-animate.js',
      'common/lib/angular-mocks/angular-mocks.js',
      'user/js/common/modules/headerModule.js',
      'user/js/common/directives/animation/animationModule.js',
      'user/js/common/modules/thumbnailModule.js',
      'user/js/common/modules/PopupSupport.js',
      'user/js/common/modules/mediaPlayer.js',
      'user/js/common/modules/filterModule.js',
      'user/js/common/modules/lazyLoadModule.js',
      'user/js/common/**/*.js',
      'user/js/introduction/**/*.js',
      'user/js/EditorsChoice/**/*.js',
      'user/js/songs/services/songsContentService.js',
      'user/js/songs/songDetailsApp.js',
      'user/js/songs/**/*.js',
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
