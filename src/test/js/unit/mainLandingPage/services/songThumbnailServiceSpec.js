'use strict';

describe("Song Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(songThumbnailService) {
        service = songThumbnailService;
    }));

	it("should get songs on the landing page thumbnails", function(){
	    var songsSampleResponse =
                    [{
                        "id":1,
                        "contentType": "Songs",
                        "songCategory": {"name":"Song & Reflection"},
                        "songTitle":{
                           "englishTranslation": "Practice the art of dying",
                        },
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
                    }];

        var landingPageThumbnails = service.getThumbnailsWithBubble(songsSampleResponse);

        expect(landingPageThumbnails).toBe('<song id="song_1" open="detailsService.open(\'song_1\')" custom-style="shift4"'+
            ' img-src="imgId"'+
            ' url="videoId"'+
            ' name="Practice the art of dying"'+
            ' category-name="Song & Reflection"'+
            ' duration="09:11"'+
            ' singer="Parvathy Baul"'+
            ' class = "songs"' +
            ' poet="Sharath">'+
            '</song>');
	});
});