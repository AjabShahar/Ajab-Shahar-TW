'use strict';

describe("introduction popup service Specs", function(){
	var service;

    beforeEach(module('htmlGenerator'));

    beforeEach(inject(function(introductionPopupService) {
        service = introductionPopupService;
    }));

    it("should get popup for songs", function(){
        var songsSampleResponse ={"songs":
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
        };

        var landingPageThumbnails = service.getPopupDetails(songsSampleResponse);

        expect(landingPageThumbnails).toBe('<pop-up id="oid0" width="100" show="popupService.shouldShow(\'oid0\')" on-close="popupService.onClose(\'oid0\')" closeButton="true">'+
            '<song-introduction singer="Parvathy Baul" name="Practice the art of dying" url="videoId" close-video="popupService.isClosed(\'oid0\')"></song-introduction></pop-up>');
    });

});