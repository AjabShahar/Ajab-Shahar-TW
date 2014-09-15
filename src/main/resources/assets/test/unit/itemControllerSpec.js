'use strict';

define('itemControllerSpec', ['app', 'angularMocks'],
  function(app, angularMocks) {

    describe('Item controller', function() {
      describe('watch model changes', function() {
        var scope, itemController;

        var phone = {
          'age': 6,
          'id': 'nexus-s',
          'name': 'Nexus S',
        };

        var ratingService = {
          setRating: function(phoneId, rating) {}
        };

        beforeEach(module('ngtut'));

        beforeEach(inject(function($rootScope, $controller) {
          scope = $rootScope.$new();
          scope.phone = phone;
          spyOn(ratingService, 'setRating');

          itemController = $controller('ItemController', {
            $scope: scope,
            ratingService: ratingService
          });
        }));


        it('should set user rating to Rating Service', function() {
          scope.phone.rating = 5;
          scope.$apply();
          expect(ratingService.setRating).toHaveBeenCalled();
        });

      });
    });
  });
