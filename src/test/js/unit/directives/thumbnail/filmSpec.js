'use strict';

describe('Content', function() {

    describe('film content', function() {
        var scope;
        var element;
        var compile;
        var template;
        beforeEach(function(){
            module('thumbnailModule');
        });

        beforeEach(inject(function($rootScope,$compile,$templateCache) {
            scope = $rootScope.$new();
            compile = $compile;
            template = $templateCache;
        }));

        it('Should initialize film with details', function() {
            scope.name1='some Name';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.context1='context';
            scope.overlay1='overlayId',

            element = angular.element('<film-with-details overlay-id="{{overlay1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" context="{{context1}}" name="{{name1}}"></film-with-details>');
            template.put('/js/common/templates/thumbnail/film.html', '{{overlayId}} {{customStyle}} {{imgSrc}} {{context}} {{name}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('overlayId someStyle someimg context some Name');
        });
    });
});
