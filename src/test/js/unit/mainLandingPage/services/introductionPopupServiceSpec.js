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
                    "songTitle":{
                        "englishTranslation": "Practice the art of dying",
                     },
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

        expect(landingPageThumbnails).toBe('<pop-up ng-init="detailsService.init(0,\'song_2\')" id="song_2" width="100" on-next="detailsService.next(0)" on-prev="detailsService.prev(0)" show="detailsService.shouldShow(\'song_2\')" on-close="detailsService.onClose(\'song_2\')" closeButton="true">'+
            '<song-introduction id="song_2" singer="Parvathy Baul" name="Practice the art of dying" audio-Url="scId" video-Url="videoId" close-video="detailsService.isClosed(\'song_2\')"></song-introduction></pop-up>');
    });

});