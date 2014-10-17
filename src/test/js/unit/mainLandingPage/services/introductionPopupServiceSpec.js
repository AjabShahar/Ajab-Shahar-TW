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
                        "category": "Songs",
                        "categoryName": "Song & Reflection",
                        "name": "Practice the art of dying",
                        "poet": "Sharath",
                        "youtubeVideoId": "videoId",
                        "imageUrl": "imgId",
                        "singer": "Parvathy Baul",
                        "duration": "09:11"
                    }
                ]
        ;

        var landingPageThumbnails = service.getPopupDetails(songsSampleResponse);

        expect(landingPageThumbnails).toBe('<pop-up id="oid0" width="100" show="shouldShow(\'oid0\')" on-close="onClose(\'oid0\')" closeButton="true">'+
            '<song-introduction singer="Parvathy Baul" name="Practice the art of dying" url="videoId"></song-introduction></pop-up>');
    });

});