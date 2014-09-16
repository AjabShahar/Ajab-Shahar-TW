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

        it('Should initialize image with details', function() {
            scope.name1='some Name';
            scope.singer1='Some Singer';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            element = angular.element('<image-with-details custom-style="{{customStyle1}}" shift-index="{{shiftIndex1}}" img-src="{{imgSrc1}}" name="{{name1}}" singer="{{singer1}}"></image-with-details>');
            template.put('js/templates/content/song.html', '{{name}} {{singer}} {{imgSrc}} {{customStyle}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('some Name Some Singer someimg someStyle');
        });
    });
});
