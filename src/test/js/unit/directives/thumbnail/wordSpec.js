'use strict';

describe('Content', function() {

    describe('word content', function() {
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

        it('Should initialize word with details', function() {
            scope.name1='some Name';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.contextualMeaning1='meaning';
            scope.overlay1='overlayId',

            element = angular.element('<word-with-details overlay-id="{{overlay1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" name="{{name1}}" contextual-meaning="{{contextualMeaning1}}"></word-with-details>');
            template.put('/js/common/templates/thumbnail/word.html', '{{overlayId}} {{customStyle}} {{imgSrc}} {{name}} {{contextualMeaning}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('overlayId someStyle someimg some Name meaning');
        });
    });
});
