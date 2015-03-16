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
            scope.name2='some Name2';
            scope.singer1='Some Singer';
            scope.imgSrc1='someimg';
            scope.customStyle1='someStyle';
            scope.categoryName1='category';
            scope.duration1='duration';
            scope.poet1='poet',

            element = angular.element('<song-thumbnail category-name="{{categoryName1}}" duration="{{duration1}}" poet="{{poet1}}" custom-style="{{customStyle1}}" img-src="{{imgSrc1}}" english-transliteration="{{name1}}" english-translation="{{name2}}" singer="{{singer1}}"></song-thumbnail>');
            template.put('/user/js/common/templates/songs/songThumbnail.html', '<div>{{categoryName}} {{duration}} {{poet}} {{englishTransliteration}} {{englishTranslation}} {{singer}} {{imgSrc}} {{customStyle}}</div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('category duration poet some Name some Name2 Some Singer someimg someStyle');
        });
    });
});
