'use strict';

describe("Reflection Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(reflectionThumbnailService) {
        service = reflectionThumbnailService;
    }));

    it("should get reflections on the landing page thumbnails", function(){
	    var reflectionsSampleResponse =
                        {
                            "contentType":"Reflections",
                            "name":"The Ulatbansi of Kabir",
                            "by":"Linda Hess",
                            "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                        };

        var landingPageThumbnails = service.getThumbnailWithBubble(reflectionsSampleResponse,"oid0","shift4");
        expect(landingPageThumbnails).toBe('<reflection-with-details overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="The Ulatbansi of Kabir" by="Linda Hess"></reflection-with-details>');
	});

});