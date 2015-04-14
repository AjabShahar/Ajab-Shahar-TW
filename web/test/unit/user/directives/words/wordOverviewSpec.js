'use strict';

describe('Word overview specs', function () {
    var $compile,
        scope,
        template;

    beforeEach(module('word'));

    beforeEach(inject(function (_$compile_, _$rootScope_, $templateCache) {
        $compile = _$compile_;
        scope = _$rootScope_.$new();
        template = $templateCache;
    }));

    it('Should replace the element with the appropriate content', function () {
        scope.dummyData = {
            word: {
                id: 1,
                wordTransliteration: "Transliteration",
                wordTranslation: "Translated",
                wordIntroEnglish: "English",
                "writers": ["", ""],
                displayAjabShaharTeam: true
            }
        };

        var wordOverviewDirective = '<word-overview id="dummyData.word.id" word-transliteration="{{dummyData.word.wordTransliteration}}" word-translation="{{dummyData.word.wordTranslation}}" word-intro-english="{{dummyData.word.wordIntroEnglish}}" writers="dummyData.word.writers" display-ajab-shahar-team="{{dummyData.word.displayAjabShaharTeam}}"></word-overview>';
        template.put('/user/js/words/directives/templates/wordOverview.html', '{{wordTransliteration}} {{wordTranslation}} {{wordIntroEnglish}} {{displayAjabShaharTeam}}');

        var element = $compile(wordOverviewDirective)(scope);
        scope.$apply();

        expect(element.html()).toContain("Transliteration Translated English true");
    });
});