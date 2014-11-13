'use strict';

describe("Word Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(wordThumbnailService) {
        service = wordThumbnailService;
    }));

    it("should get words on the landing page thumbnails", function(){
	    var wordsSampleResponse =
                    {
                        "contentType": "Words",
                        "categoryName": "WORD INTRO",
                        "name": "Untellable Tale",
                        "contextualMeaning": "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...",
                        "thumbnail_url": "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                    };

        var landingPageThumbnails = service.getThumbnailWithBubble(wordsSampleResponse,"oid0","shift4");
        expect(landingPageThumbnails).toBe('<word overlay-id="oid0" custom-style="shift4"'+
         ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
         ' name="Untellable Tale" contextual-meaning="An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description..."></word>');
	});
});