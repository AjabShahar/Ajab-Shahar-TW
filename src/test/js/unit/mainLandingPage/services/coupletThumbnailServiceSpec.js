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
                                "categoryName":"Couplet",
                                "englishTranslation":"A pot in water, water in a pot...",
                                "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            };

        var landingPageThumbnails = service.getThumbnailWithBubble(coupletsSampleResponse,"oid0","shift4");
        expect(landingPageThumbnails).toBe('<couplet-with-details overlay-id="oid0" custom-style="shift4"'+
                                           ' title="A pot in water, water in a pot..."'+
                                           ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                           '</couplet-with-details>');
	});
});