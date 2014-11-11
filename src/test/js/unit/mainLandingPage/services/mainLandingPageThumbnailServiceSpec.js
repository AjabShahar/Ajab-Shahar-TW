'use strict';

describe("Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(mainLandingPageThumbnailService) {
        service = mainLandingPageThumbnailService;
    }));

	it("should get songs on the landing page thumbnails", function(){
	    var songsSampleResponse ={"songs":
	    	            [
                            {
                                "id":0,
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
                            }
                        ]};

        var landingPageThumbnails = service.getThumbnailWithBubble(songsSampleResponse);

        expect(landingPageThumbnails).toBe('<song id="song_0" open="detailsService.open(\'song_0\')" custom-style="shift4"'+
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

	it("should get films on the landing page thumbnails", function(){
	    var filmsSampleResponse = {"films":
	                [
                                {
                                    "contentType":"Films",
                                    "categoryName":"Film Episode",
                                    "context":"Prahlad Tipanya Meets His Guru",
                                    "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                                    "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                                    "name":"KOI SUNTA HAI",
                                    "duration":"21 : 00"
                                }
                    ]
	    };

        var landingPageThumbnails = service.getThumbnailWithBubble(filmsSampleResponse);
        expect(landingPageThumbnails).toBe('<film-with-details overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="KOI SUNTA HAI" context="Prahlad Tipanya Meets His Guru"></film-with-details>');
	});

    it("should get reflections on the landing page thumbnails", function(){
	    var reflectionsSampleResponse = {
	        "reflections":            [
                                                  {
                                                      "contentType":"Reflections",
                                                      "name":"The Ulatbansi of Kabir",
                                                      "by":"Linda Hess",
                                                      "videoUrl":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                                                      "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                                                  }
                                      ]
	    };

        var landingPageThumbnails = service.getThumbnailWithBubble(reflectionsSampleResponse);
        expect(landingPageThumbnails).toBe('<reflection-with-details overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="The Ulatbansi of Kabir" by="Linda Hess"></reflection-with-details>');
	});

    it("should get words on the landing page thumbnails", function(){
	    var wordsSampleResponse ={"words":
	                [
                            {
                                "contentType": "Words",
                                "categoryName": "WORD INTRO",
                                "name": "Untellable Tale",
                                "contextualMeaning": "An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description...",
                                "thumbnail_url": "http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                            }
                    ]
	    };

        var landingPageThumbnails = service.getThumbnailWithBubble(wordsSampleResponse);
        expect(landingPageThumbnails).toBe('<word-with-details overlay-id="word_0" custom-style="shift4"'+
         ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
         ' name="Untellable Tale" contextual-meaning="An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description..."></word-with-details>');
	});

    it("should get gatherings on the landing page thumbnails", function(){
	    var gatheringsSampleResponse ={"gatherings":
	                [
                                    {
                                        "contentType":"Gathering",
                                        "name":"Bangalore Festival Of Kabir",
                                        "location":"Bangalore",
                                        "date":"2009",
                                        "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"
                                    }
                    ]
	    };

        var landingPageThumbnails = service.getThumbnailWithBubble(gatheringsSampleResponse);

        expect(landingPageThumbnails).toBe('<gathering-with-details overlay-id="oid0" custom-style="shift4"'+
                                            ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                            ' name="Bangalore Festival Of Kabir" location="Bangalore" date="2009"></gathering-with-details>');
	});

    it("should get couplets on the landing page thumbnails", function(){
	    var coupletsSampleResponse ={"couplets":
	                [
                                    {
                                        "contentType":"Couplets",
                                        "category":{
                                         "name":"Couplet",
                                        },
                                        "englishTranslation":"A pot in water, water in a pot...",
                                        "englishTranslationText":"A pot in water, water in a pot",
                                        "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                                    },
                    ]
	    };

        var landingPageThumbnails = service.getThumbnailWithBubble(coupletsSampleResponse);
        expect(landingPageThumbnails).toBe('<couplet-with-details overlay-id="couplet_0" custom-style="shift4"'+
                                           ' title="A pot in water, water in a pot..."'+
                                           ' english-translation-text="A pot in water, water in a pot"'+
                                           ' category-name="Couplet"'+
                                           ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                           '</couplet-with-details>');
	});
});