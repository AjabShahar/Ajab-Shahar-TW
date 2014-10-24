var fb = angular.module('fb',[]);

fb.provider ('facebook', function facebookProvider ($injector) {
    this.initialized = false;
    var defaultParams = { appId: '714039208665351', status: true, cookie: true, xfbml: true };
    var facebookEvents = {
        'auth': ['authResponseChange', 'statusChange', 'login', 'logout']
    };

    var Q = [];

    this.init = function (params) {
        window.fbAsyncInit = function() {
            angular.extend (defaultParams, params);
            FB.init(defaultParams);
    
            this.initialized = true;
            console.log ("Facebook initialization done.");

            processPostInitializeQ ();
        };
    };

    function executeWhenInitialized (callback, self, args) {
        console.log ("adding to Q: ", callback);
        Q.push ([callback, self, args]);
    };


    var processPostInitializeQ = function () {
        console.log ('Processing Q messages.');
        while (item = Q.shift ()) {

            func = item[0];
            self = item[1];
            args = item[2];

            func.apply (self, args);
        }
    };


    this.$get = ["$rootScope", "$q",  function ($rootScope, $q) {
        var promise = function (func) {
            var deferred = $q.defer ();

            func (function (response) {
                if (response && response.error) {
                    deferred.reject (response);
                } else {
                    deferred.resolve (response);
                }

                $rootScope.$apply ();
            });

            return deferred.promise;
        };

        var registerEventHandlers = function () {
            angular.forEach (facebookEvents, function (events, domain) {
                angular.forEach (events, function (_event) {
                    FB.Event.subscribe (domain + '.' + _event, function (response) {
                        $rootScope.$broadcast('fb.' + domain + '.' + _event, response);
                    });
                });
            });
        };
 
        var login = function (params) {
            return promise (function (callback) {
                FB.login (function (response) {
                    callback (response);
                }, params);
            });
        }
       
        var api = function (path) {
            return promise (function (callback) {
                FB.api (path, function (response) {
                    callback (response);
                });
            });
        }

        if (!this.initialized) {
            executeWhenInitialized (registerEventHandlers, this, []);
        } else {
            registerEventHandlers ();
        }

        var provider = this;
        return  {
            initialized: function () {
                return provider.initialized;
            },

            init: provider.init,
            api: api,
            login: login
        }
    }];
});

fb.directive ('facebook', function ($location, facebook) {
    var template = 
        "<div id='fb-root'><script type='text/javascript' async='true' src='" + 
        "//connect.facebook.net/en_US/all.js' id='facebook-jssdk'></script></div>";

    return {
        restrict:'EA',
        template: template,

        scope: {
            appId: '@',
            parameters: '='
        },

        link: function (scope, element, attrs) {
            if (!facebook.initialized ()) {
                var parameters = scope.parameters || {};

                angular.extend (parameters, {appId: scope.appId});
                facebook.init (parameters);
            }
        }
    }
});

fb.directive ('facebookLogin', function () {
    var template =
        '<div class="fb-login-button" ' +
        'data-max-rows="1" ' +
        'data-size="{{size||\'medium\'}}" ' +
        'data-show-faces="{{!!showFaces}}" ' +
        'data-auto-logout-link="{{!!autoLogout}}" ' +
        'data-scope="{{scope || \'basic_info\'}}"' +
        '></div>';

    return {
        restrict: 'E',
        scope: {
            'autoLogout': '@',
            'size': '@',
            'showFaces': '@',
            'scope': '@'
        },
        template: template
    }
});

fb.directive('facebookLike', function ($location) {
    var template = '<div class="fb-like" ' +
        'data-href="{{href || currentPage}}" ' +
        'data-colorscheme="{{colorScheme || \'light\'}}" ' +
        'data-layout="{{layout || \'standard\'}}" ' +
        'data-action="{{ action || \'like\'}}" ' +
        'data-show-faces="{{!!showFaces}}" ' +
        'data-share="{{!!share}}"' +
        'data-action="{{action || \'like\'}}"' +
        'data-send="false"></div>';

    return {
        restrict:'E',
        scope: {
            'colorScheme': '@',
            'layout':      '@',
            'showFaces':   '@',
            'href':        '@',
            'action':      '@',
            'share':       '@',
        },
        template: template,
        link: function(scope, element, attrs) {
            scope.currentPage = $location.absUrl();
        },
    }
});