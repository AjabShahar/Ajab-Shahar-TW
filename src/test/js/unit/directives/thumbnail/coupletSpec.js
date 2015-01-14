'use strict';

describe('Content', function() {

    describe('couplet content', function() {
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

        it('Should initialize couplet with details', function() {
            scope.name1='some Name';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.overlayId1='overlayId';

            element = angular.element('<couplet-thumbnail overlay-id="{{overlayId1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" name="{{name1}}"></couplet-thumbnail>');
            template.put('/user-js/common/templates/thumbnail/coupletThumbnail.html', '<div>{{overlayId}} {{customStyle}} {{name}} {{imgSrc}}</div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe(' someStyle  someimg');
        });
    });
});
