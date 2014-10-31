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
                    "id":2,
                    "contentType": "Songs",
                    "categoryName": "Song & Reflection",
                    "englishTranslation": "Practice the art of dying",
                    "poet": "Sharath",
                    "youtubeVideoId": "videoId",
                    "soundCloudTrackID": "scId",
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

        expect(landingPageThumbnails).toBe('<pop-up ng-init="popupService.init(0,\'song_2\')" id="song_2" width="100" on-next="popupService.next(0)" on-prev="popupService.prev(0)" show="popupService.shouldShow(\'song_2\')" on-close="popupService.onClose(\'song_2\')" closeButton="true">'+
            '<song-introduction id="song_2" singer="Parvathy Baul" name="Practice the art of dying" audio-Url="scId" url="videoId" close-video="popupService.isClosed(\'song_2\')"></song-introduction></pop-up>');
    });

});