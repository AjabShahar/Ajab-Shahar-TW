'use strict';

describe("introduction popup service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(introductionPopupService) {
        service = introductionPopupService;
    }));

    it("should get popup for songs", function(){
        var songsSampleResponse =
                [
                    {
                        "contentType": "Songs",
                        "categoryName": "Song & Reflection",
                        "englishTranslationTitle": "Practice the art of dying",
                        "poet": "Sharath",
                        "youtubeVideoId": "videoId",
                        "imageUrl": "imgId",
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
                ]
        ;

        var landingPageThumbnails = service.getPopupDetails(songsSampleResponse);

        expect(landingPageThumbnails).toBe('<pop-up id="oid0" width="100" show="shouldShow(\'oid0\')" on-close="onClose(\'oid0\')" closeButton="true">'+
            '<song-introduction singer="Parvathy Baul" name="Practice the art of dying" url="videoId" close-video="isClosed(\'oid0\')"></song-introduction></pop-up>');
    });

});