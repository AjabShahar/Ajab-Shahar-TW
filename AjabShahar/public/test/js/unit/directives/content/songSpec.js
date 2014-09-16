'use strict';

describe('Content', function() {

    describe('song content', function() {
        var scope;
        var element;
        var compile;
        var template;
        beforeEach(function(){
            module('contentModule');
        });

        beforeEach(inject(function($rootScope,$compile,$templateCache) {
            scope = $rootScope.$new();
            compile = $compile;
            template = $templateCache;
        }));

        it('Should initialize image with details and shift right', function() {
            scope.name1='some Name';
            scope.singer1='Some Singer';
            scope.imgSrc1='someimg';
            scope.shiftDirection1='r';
            scope.shiftIndex1='2';
            element = angular.element('<image-with-details shift-direction="{{shiftDirection1}}" shift-index="{{shiftIndex1}}" img-src="{{imgSrc1}}" name="{{name1}}" singer="{{singer1}}"></image-with-details>');
            template.put('js/templates/content/image.html', '{{name}} {{singer}} {{imgSrc}} {{shift()}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('some Name Some Singer someimg shiftRight2');
        });

        it('Should initialize image with details and shift left', function() {
            scope.name1='some Name';
            scope.singer1='Some Singer';
            scope.imgSrc1='someimg';
            scope.shiftDirection1='l';
            scope.shiftIndex1='2';
            element = angular.element('<image-with-details shift-direction="{{shiftDirection1}}" shift-index="{{shiftIndex1}}" img-src="{{imgSrc1}}" name="{{name1}}" singer="{{singer1}}"></image-with-details>');
            template.put('js/templates/content/image.html', '{{name}} {{singer}} {{imgSrc}} {{shift()}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('some Name Some Singer someimg shiftLeft2');
        });

        it('Should initialize image with details and no shift', function() {
            scope.name1='some Name';
            scope.singer1='Some Singer';
            scope.imgSrc1='someimg';
            element = angular.element('<image-with-details img-src="{{imgSrc1}}" name="{{name1}}" singer="{{singer1}}"></image-with-details>');
            template.put('js/templates/content/image.html', '{{name}} {{singer}} {{imgSrc}} {{shift()}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('some Name Some Singer someimg ');
        });
    });
});
