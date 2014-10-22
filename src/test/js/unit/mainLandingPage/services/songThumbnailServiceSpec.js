'use strict';

describe("Song Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(songThumbnailService) {
        service = songThumbnailService;
    }));

	it("should get songs on the landing page thumbnails", function(){
	    var songsSampleResponse =
                    {
                        "contentType": "Songs",
                        "categoryName": "Song & Reflection",
                        "englishTranslationTitle": "Practice the art of dying",
                        "poets": [
                            {
                            "category": "POET",
                            "firstName": "Sharath",
                            "middleName": "",
                            "lastName": ""
                            }
                            ],

                        "youtubeVideoId": "videoId",
                        "thumbnail_url": "imgId",
                        "singers": [
                            {
                            "category": "SINGER",
                            "firstName": "Parvathy",
                            "middleName": "",
                            "lastName": "Baul"
                            }
                            ],
                        "duration": "09:11"
                    };
        ;

        var landingPageThumbnails = service.getThumbnailWithBubble(songsSampleResponse,"oid0","shift4");

        expect(landingPageThumbnails).toBe('<song-with-details overlay-id="oid0" open="open(\'oid0\')" custom-style="shift4"'+
            ' img-src="imgId"'+
            ' url="videoId"'+
            ' name="Practice the art of dying"'+
            ' category-name="Song & Reflection"'+
            ' duration="09:11"'+
            ' singer="Parvathy Baul"'+
            ' poet="Sharath">'+
            '</song-with-details>');
	});
});