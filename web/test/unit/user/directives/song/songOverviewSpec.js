'use strict';

describe("Song overview specs:", function () {
    var $compile,
        scope,
        template;

    beforeEach(module('thumbnailModule'));

    beforeEach(inject(function (_$compile_, _$rootScope_, $templateCache) {
        $compile = _$compile_;
        scope = _$rootScope_.$new();
        template = $templateCache;
    }));

    describe("When the directive is loaded,", function () {
        it("then should replace the element with the appropriate content", function () {
            scope.overview = {
                id: 1,
                singers: "singer",
                poet: "poet",
                englishTranslation: "englishTranslation",
                audioId: "audioId",
                videoId: "videoId",
                englishTransliteration: "englishTransliteration",
                noun: "noun",
                words: "words"
            };
            var songOverviewDirective = '<song-overview id="{{overview.id}}" singers="overview.singers" poet="overview.poet" english-translation="{{overview.englishTranslation}}" audio-Url="{{overview.audioId}}" video-Url="{{overview.videoId}}" english-transliteration="{{overview.englishTransliteration}}" noun="{{overview.noun}}" words="overview.words"></song-overview>';
            template.put('/user/js/common/templates/songs/songOverview.html', '{{id}} {{singers}} {{poet}} {{englishTranslation}} {{audioUrl}} {{videoUrl}} {{englishTransliteration}} {{noun}} {{words}}');

            var element = $compile(songOverviewDirective)(scope);
            scope.$apply();

            expect(element.html()).toBe('1 singer poet englishTranslation audioId videoId englishTransliteration noun words');
        });
    });
});
