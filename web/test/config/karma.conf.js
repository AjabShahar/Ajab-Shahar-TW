'use strict';
module.exports = function (config) {
    config.set({
        basePath: '../../app',
        files: [
            'common/lib/jquery/dist/jquery.min.js',
            'common/lib/jcarousel/dist/jquery.jcarousel.js',
            'common/lib/underscore/underscore-min.js',
            'common/lib/angular/angular.js',
            'common/lib/angular-animate/angular-animate.js',
            'common/lib/angular-route/angular-route.js',
            'common/lib/angular-mocks/angular-mocks.js',
            'common/lib/angular-multi-select/angular-multi-select.js',
            'common/lib/textAngular/dist/textAngular-sanitize.min.js',
            'common/lib/textAngular/dist/textAngular.min.js',
            'common/lib/ngInfiniteScroll/build/ng-infinite-scroll.min.js',
            'common/lib/angular-socialshare/angular-socialshare.js',
            'admin/js/services/**/*.js',
            'admin/js/person/**/*.js',
            'admin/js/words/**/*.js',
            'admin/js/reflections/**/*.js',
            'admin/js/gatherings/**/*.js',
            'admin/js/**/*.js',
            'user/js/common/modules/headerModule.js',
            'user/js/common/directives/animation/animationModule.js',
            'user/js/common/modules/thumbnailModule.js',
            'user/js/common/modules/PopupSupport.js',
            'user/js/common/modules/mediaPlayer.js',
            'user/js/common/modules/filterModule.js',
            'user/js/common/modules/lazyLoadModule.js',
            'user/js/common/modules/utilities.js',
            'user/js/mappers.js',
            'user/js/common/**/*.js',
            'user/js/introduction/**/*.js',
            'user/js/featuredContent/**/*.js',
            'user/js/songs/services/songsContentService.js',
            'user/js/reflections/reflectionsApp.js',
            'user/js/reflections/services/reflectionsContentService.js',
            'user/js/reflections/controllers/allReflectionsController.js',
            'user/js/songs/songDetailsApp.js',
            'user/js/songs/explore/songsApp.js',
            'user/js/songs/**/*.js',
            'user/js/words/wordApp.js',
            'user/js/words/**/*.js',
            'user/js/reflections/**/*.js',
            'http://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-cookies.js',
            '../test/unit/**/*.js',
            'user/js/people/peopleApp.js',
            'user/js/people/**/*.js',
            'user/js/common/directives/carouselSupport/carousel.html',
            'user/js/common/directives/contentDetails/contentDetails.html'
        ],
        autoWatch: true,
        frameworks: ['jasmine'],
        browsers: ['PhantomJS'],
        reporters: ['dots', 'junit', 'coverage'],
        plugins: [
            'karma-phantomjs-launcher',
            'karma-jasmine',
            'karma-ng-html2js-preprocessor'
        ],
        preprocessors: {
            '**/*.js': ['coverage'],
            'user/js/common/directives/carouselSupport/carousel.html':['ng-html2js'],
            'user/js/common/directives/contentDetails/contentDetails.html':['ng-html2js']
        },

        ngHtml2JsPreprocessor: {
            prependPrefix: '/',
            // setting this option will create only a single module that contains templates
            // from all the files, so you can load them all with module('foo')
            moduleName: 'testTemplate'
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
                    type: 'html',
                    dir: '../coverage/'
                },
                {
                    type: 'text-summary'
                }
            ]
        }
    });
};
