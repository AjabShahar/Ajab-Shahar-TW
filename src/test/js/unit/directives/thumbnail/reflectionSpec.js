'use strict';

describe('Content', function() {

    describe('reflection content', function() {
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

        it('Should initialize reflection with details', function() {
            scope.name1='some Name';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.by1='by';
            scope.overlay1='overlayId',

            element = angular.element('<reflection-thumbnail overlay-id="{{overlay1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" name="{{name1}}" by="{{by1}}"></reflection-thumbnail>');
            template.put('/user/js/common/templates/thumbnail/reflectionThumbnail.html', '<div>{{overlayId}} {{customStyle}} {{imgSrc}} {{name}} {{by}}</div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('overlayId someStyle someimg some Name by');
        });
    });
});
