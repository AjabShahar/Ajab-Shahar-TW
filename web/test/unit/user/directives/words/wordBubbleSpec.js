'use strict';

xdescribe('Word bubble specs', function () {
    var $compile,
        scope,
        template;

    beforeEach(module('thumbnailModule'));

    beforeEach(inject(function (_$compile_, _$rootScope_, $templateCache) {
        $compile = _$compile_;
        scope = _$rootScope_.$new();
        template = $templateCache;
    }));

    it('Should replace the element with the appropriate content', function () {
        scope.dummyData = {
            word: {
                wordOriginal: "Original",
                wordTranslation: "Translated",
                wordTransliteration: "Transliterated",
                wordIntroductions: [
                    {
                        introTextTransliteration: "IntroTextTransliteration"
                    }
                ]
            }
        };

        var wordBubbleDirective = '<word-bubble word-transliteration="{{dummyData.word.wordTransliteration}}" word-translation="{{dummyData.word.wordTranslation}}" transliteration-intro="{{dummyData.word.wordIntroductions[0].introTextTransliteration}}" word-original="{{dummyData.word.wordOriginal}}"></word-bubble>';
        template.put('/user/js/words/directives/templates/wordBubble.html', '<div> {{wordTransliteration}} {{wordTranslation}} {{transliterationIntro}} </div>');

        var element = $compile(wordBubbleDirective)(scope);
        scope.$apply();

        expect(element.html()).toContain('<div class="ng-binding"> Transliterated Translated IntroTextTransliteration </div>');
    });
});
