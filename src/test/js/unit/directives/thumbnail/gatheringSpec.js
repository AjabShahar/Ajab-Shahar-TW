'use strict';

describe('Content', function() {

    describe('gathering content', function() {
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

        it('Should initialize gathering with details', function() {
            scope.name1='some Name';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.location1='location';
            scope.date1='date';
            scope.overlay1='overlayId',

            element = angular.element('<gathering overlay-id="{{overlay1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" name="{{name1}}" location="{{location1}}" date="{{date1}}"></gathering>');
            template.put('/user/js/common/templates/thumbnail/gathering.html', '{{overlayId}} {{customStyle}} {{imgSrc}} {{name}} {{date}} {{location}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('overlayId someStyle someimg some Name date location');
        });
    });
});
