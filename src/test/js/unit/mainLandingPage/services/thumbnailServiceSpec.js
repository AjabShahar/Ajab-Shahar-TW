'use strict';

describe("Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(thumbnailService) {
        service = thumbnailService;
    }));

	it("should get songs on the landing page thumbnails", function(){
	    var songsSampleResponse =
	            [
                    {
                        "category": "SONG",
                        "categoryName": "Song & Reflection",
                        "englishTranslationTitle": "Practice the art of dying",
                        "poet": [
                            {
                            "category": "POET",
                            "firstName": "Sharath",
                            "middleName": "",
                            "lastName": ""
                            }
                            ],

                        "youtubeVideoId": "videoId",
                        "imageUrl": "imgId",
                        "singer": [
                            {
                            "category": "SINGER",
                            "firstName": "Parvathy",
                            "middleName": "",
                            "lastName": "Baul"
                            }
                            ],
                        "duration": "09:11"
                    }
                ]
        ;

        var landingPageThumbnails = service.getThumbnailWithBubble(songsSampleResponse);

        expect(landingPageThumbnails).toBe('<song-with-details overlay-id="oid0" open="open(\'oid0\')" custom-style="shift4"'+
            ' img-src="imgId"'+
            ' url="videoId"'+
            ' name="Practice the art of dying" singer="Parvathy Baul" category-name="Song & Reflection"'+
            ' duration="09:11"'+
            ' poet="Sharath"></song-with-details>');
	});

	it("should get films on the landing page thumbnails", function(){
	    var filmsSampleResponse =
            [
                        {
                            "category":"Films",
                            "categoryName":"Film Episode",
                            "context":"Prahlad Tipanya Meets His Guru",
                            "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            "name":"KOI SUNTA HAI",
                            "duration":"21 : 00"
                        }
            ];

        var landingPageThumbnails = service.getThumbnailWithBubble(filmsSampleResponse);
        expect(landingPageThumbnails).toBe('<film-with-details overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="KOI SUNTA HAI" context="Prahlad Tipanya Meets His Guru"></film-with-details>');
	});

    it("should get reflections on the landing page thumbnails", function(){
	    var reflectionsSampleResponse =
            [
                        {
                            "category":"Reflections",
                            "name":"The Ulatbansi of Kabir",
                            "by":"Linda Hess",
                            "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                        }
            ];

        var landingPageThumbnails = service.getThumbnailWithBubble(reflectionsSampleResponse);
        expect(landingPageThumbnails).toBe('<reflection-with-details overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="The Ulatbansi of Kabir" by="Linda Hess"></reflection-with-details>');
	});

    it("should get words on the landing page thumbnails", function(){
	    var wordsSampleResponse =
            [
                    {
                        "category": "Words",
                        "categoryName": "WORD INTRO",
                        "name": "Untellable Tale",
                        "contextualMeaning": "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...",
                        "imageUrl": "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                    }
            ];

        var landingPageThumbnails = service.getThumbnailWithBubble(wordsSampleResponse);
        expect(landingPageThumbnails).toBe('<word-with-details overlay-id="oid0" custom-style="shift4"'+
         ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
         ' name="Untellable Tale" contextual-meaning="An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description..."></word-with-details>');
	});

    it("should get gatherings on the landing page thumbnails", function(){
	    var gatheringsSampleResponse =
            [
                            {
                                "category":"Gathering",
                                "name":"Bangalore Festival Of Kabir",
                                "location":"Bangalore",
                                "date":"2009",
                                "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                            }
            ];

        var landingPageThumbnails = service.getThumbnailWithBubble(gatheringsSampleResponse);

        expect(landingPageThumbnails).toBe('<gathering-with-details overlay-id="oid0" custom-style="shift4"'+
                                            ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                            ' name="Bangalore Festival Of Kabir" location="Bangalore" date="2009"></gathering-with-details>');
	});

    it("should get couplets on the landing page thumbnails", function(){
	    var coupletsSampleResponse =
            [
                            {
                                "category":"Couplets",
                                "categoryName":"Couplet",
                                "name":"A pot in water, water in a pot...",
                                "imageUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            },
            ];

        var landingPageThumbnails = service.getThumbnailWithBubble(coupletsSampleResponse);
        expect(landingPageThumbnails).toBe('<couplet-with-details overlay-id="oid0" custom-style="shift4"'+
                                           ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                           ' name="A pot in water, water in a pot..."</couplet-with-details>');
	});
});