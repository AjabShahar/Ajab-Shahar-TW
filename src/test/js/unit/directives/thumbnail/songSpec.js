'use strict';

describe('Content', function() {

    describe('song content', function() {
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

        it('Should initialize song with details', function() {
            scope.name1='some Name';
            scope.singer1='Some Singer';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.categoryName1='category';
            scope.duration1='duration';
            scope.poet1='poet',

            element = angular.element('<song-with-details category-name="{{categoryName1}}" duration="{{duration1}}" poet="{{poet1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" name="{{name1}}" singer="{{singer1}}"></song-with-details>');
            template.put('/js/common/templates/thumbnail/song.html', '{{categoryName}} {{duration}} {{poet}} {{name}} {{singer}} {{imgSrc}} {{customStyle}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('category duration poet some Name Some Singer someimg someStyle');
        });
    });
});
