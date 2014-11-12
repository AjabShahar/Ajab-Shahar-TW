'use strict';

describe("Couple Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(coupletThumbnailService) {
        service = coupletThumbnailService;
    }));

    it("should get couplets on the landing page thumbnails", function(){
	    var coupletsSampleResponse =
                            {
                                "contentType":"Couplets",
                                "englishTransliteration":"Jal mein kumbh, kumbh mein jal hai...",
                                "category": {
                                   "name": "Couplet",
                                },
                                "englishTranslationText":"A pot in water, water in a pot",
                                "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            };

        var landingPageThumbnails = service.getThumbnailWithBubble(coupletsSampleResponse,"oid0","shift4");
        expect(landingPageThumbnails).toBe('<couplet-with-details id="oid0" custom-style="shift4"'+
                                           ' open="detailsService.open(\'oid0\')"'+
                                           ' title="Jal mein kumbh, kumbh mein jal hai..."'+
                                           ' english-translation-text="A pot in water, water in a pot"'+
                                           ' category-name="Couplet"'+
                                           ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                           '</couplet-with-details>');
	});
});