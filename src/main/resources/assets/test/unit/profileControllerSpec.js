'use strict';
define('profileControllerSpec', ['app', 'angularMocks'],
  function(app, angularMocks) {
    describe('Profile controller', function() {
      describe('Uses $http to load data', function() {
        var scope, profileController, httpBackend, routeParams;
        beforeEach(module('ngtut'));
        var phone = {
          'age': 0,
          'id': 'motorola-xoom-with-wi-fi',
          'name': 'Motorola XOOM'
        };

        var ratingService = {
          getRating: function(phoneId){}
        };

        beforeEach(inject(function($rootScope, $controller, $httpBackend, $routeParams) {
          scope = $rootScope.$new();
          httpBackend = $httpBackend;
          routeParams = $routeParams;
          routeParams.phoneId = 'motorola-xoom-with-wi-fi';
          httpBackend.expectGET('data/motorola-xoom-with-wi-fi.json').respond(phone);
          spyOn(ratingService, 'getRating');


          profileController = $controller('ProfileController', {
            $scope: scope,
            ratingService: ratingService
          });

        }));

        it('should GET phones from data/phones.json', function() {
          expect(scope.phone.name).toBeUndefined();
          httpBackend.flush();
          expect(scope.phone.name).toBe('Motorola XOOM');
        });

        it('should read phone rating from Rating service',function(){
          httpBackend.flush();
          expect(ratingService.getRating).toHaveBeenCalled();  
        });

      });
    });
  });
