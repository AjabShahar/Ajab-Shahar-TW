'use strict';

describe("Reflection Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(gatheringThumbnailService) {
        service = gatheringThumbnailService;
    }));

    it("should get gatherings on the landing page thumbnails", function(){
	    var gatheringsSampleResponse =
                            {
                                "contentType":"Gathering",
                                "name":"Bangalore Festival Of Kabir",
                                "location":"Bangalore",
                                "date":"2009",
                                "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                            };

        var landingPageThumbnails = service.getThumbnailWithBubble(gatheringsSampleResponse,"oid0","shift4");

        expect(landingPageThumbnails).toBe('<gathering-with-details overlay-id="oid0" custom-style="shift4"'+
                                            ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                            ' name="Bangalore Festival Of Kabir" location="Bangalore" date="2009"></gathering-with-details>');
	});
});