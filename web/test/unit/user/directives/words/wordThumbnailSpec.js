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
            scope.customStyle1='someStyle';
            scope.introduction1='meaning';
            scope.overlay1='overlayId',

            element = angular.element('<word-thumbnail overlay-id="{{overlay1}}" custom-style="{{customStyle1}}" transliteration="{{name1}}" english-intro-excerpt="{{introduction1}}"></word-thumbnail>');
            template.put('/user/js/common/templates/words/wordThumbnail.html', '<div>{{overlayId}} {{customStyle}} {{transliteration}} {{englishIntroExcerpt}}</div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('overlayId someStyle some Name meaning');
        });
    });
});
