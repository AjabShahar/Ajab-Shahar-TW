'use strict';

describe("Film Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(filmThumbnailService) {
        service = filmThumbnailService;
    }));

	it("should get films on the landing page thumbnails", function(){
	    var filmsSampleResponse =
                        {
                            "contentType":"Films",
                            "categoryName":"Film Episode",
                            "context":"Prahlad Tipanya Meets His Guru",
                            "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            "name":"KOI SUNTA HAI",
                            "duration":"21 : 00"
                        };

        var landingPageThumbnails = service.getThumbnailWithBubble(filmsSampleResponse,"oid0","shift4");
        expect(landingPageThumbnails).toBe('<film-with-details overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="KOI SUNTA HAI" context="Prahlad Tipanya Meets His Guru"></film-with-details>');
	});
});