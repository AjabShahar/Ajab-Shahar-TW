describe("sort Service",function(){
    var words, songs;
    var sortService;
    beforeEach(module("animationModule"));
    beforeEach(inject(function(_sortService_){
        sortService =  _sortService_;
    }));
    beforeEach(function(){
        words = [{
            'id':1,
            'wordTranslation':'Shoonya',
            'wordTransliteration':'Emptiness'
        },
        {
            'id':2,
            'wordTranslation':'Akath katha',
            'wordTransliteration':'Akath'
        }];
        songs = [{
            'id':1,
            'englishTranslation':'The cart of meditation is tottering',
            'englishTransliteration':'Bhajan ro gudhak rao',
            'isSong':true
        },
        {
            'id':2,
            'englishTranslation':'For few days o heart',
            'englishTransliteration':'Kichu din mone mone',
            'isSong':true
        }]
    });
    it("should sort words based on contentText representation",function(){
        words = sortService.sortList(words,'Transliteration');

        expect(words[0].id).toBe(2);
        expect(words[0].wordTransliteration).toBe('Akath');
        expect(words[1].id).toBe(1);
        expect(words[1].wordTransliteration).toBe('Emptiness');

        words = sortService.sortList(words,'Translation');

        expect(words[0].id).toBe(2);
        expect(words[0].wordTranslation).toBe('Akath katha');
        expect(words[1].id).toBe(1);
        expect(words[1].wordTranslation).toBe('Shoonya');
    });

    it("should sort songs based on contentText representation",function(){
        songs = sortService.sortList(songs,'Transliteration');

        expect(songs[0].id).toBe(1);
        expect(songs[0].englishTransliteration).toBe('Bhajan ro gudhak rao');
        expect(songs[1].id).toBe(2);
        expect(songs[1].englishTransliteration).toBe('Kichu din mone mone');

        songs = sortService.sortList(songs,'Translation');

        expect(songs[0].id).toBe(2);
        expect(songs[0].englishTranslation).toBe('For few days o heart');
        expect(songs[1].id).toBe(1);
        expect(songs[1].englishTranslation).toBe('The cart of meditation is tottering');
    });
});