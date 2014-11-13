'use strict';

describe("Thumbnail service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(mainLandingPageThumbnailService) {
        service = mainLandingPageThumbnailService;
    }));

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
        expect(landingPageThumbnails).toBe('<film overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="KOI SUNTA HAI" context="Prahlad Tipanya Meets His Guru"></film>');
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
        expect(landingPageThumbnails).toBe('<reflection overlay-id="oid0" custom-style="shift4"'+
        ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
        ' name="The Ulatbansi of Kabir" by="Linda Hess"></reflection>');
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
        expect(landingPageThumbnails).toBe('<word overlay-id="word_0" custom-style="shift4"'+
         ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
         ' name="Untellable Tale" contextual-meaning="An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description..."></word>');
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

        expect(landingPageThumbnails).toBe('<gathering overlay-id="oid0" custom-style="shift4"'+
                                            ' img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG"'+
                                            ' name="Bangalore Festival Of Kabir" location="Bangalore" date="2009"></gathering>');
	});
});