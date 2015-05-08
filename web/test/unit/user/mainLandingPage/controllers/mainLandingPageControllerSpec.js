'use strict';

describe('Main landing page controller', function () {
    var scope, $httpBackend,popupService;

    beforeEach(module("featuredContentApp"));

    beforeEach(inject(function (_$controller_, _$rootScope_, _mainLandingContentService_, _mappers_, _popupService_,_$httpBackend_) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        popupService = _popupService_;

        _$controller_('featuredContentController', {
            $scope: scope,
            mainLandingContentService: _mainLandingContentService_,
            mappers: _mappers_,
            popupService: popupService
        });

        $httpBackend.when("GET", "/api/songs/getPublishedSongs").respond(test_songs_mainfc);
        $httpBackend.when("GET", "/api/reflections?content=featured").respond(test_reflections_mainfc);
        $httpBackend.when("GET", "/api/words?showOnMainLandingPage=true").respond(test_words_mainfc);

    }));


    it("should construct the content overviews list with the same order of elements as the elements in thumbnail objects list ", function () {

        //scope.init();
        $httpBackend.flush();

        expect(popupService.getItems()[0].content.isSong).toBeTruthy();
        expect(popupService.getItems()[2].content.isSong).toBeTruthy();
        expect(popupService.getItems()[4].content.isSong).toBeTruthy();
        expect(popupService.getItems()[6].content.isSong).toBeTruthy();
        expect(popupService.getItems()[8].content.isSong).toBeTruthy();

        expect(popupService.getItems()[3].content.wordTranslation).toBe("Waking Sleeping");

        expect(popupService.getItems()[1].content.isReflection).toBeTruthy();
        expect(popupService.getItems()[5].content.isReflection).toBeTruthy();
        expect(popupService.getItems()[7].content.isReflection).toBeTruthy();

    });

});


var test_songs_mainfc = {
    "songs": [
        {
            "id": 11,
            "isAuthoringComplete": true,
            "showOnLandingPage": true,
            "youtubeVideoId": "",
            "soundCloudTrackId": "https://soundcloud.com/kashanilyas/we-main-chori-chori-tere-nal/sets",
            "thumbnailURL": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png",
            "duration": "07:45",
            "singers": [
                {
                    "id": 25,
                    "name": "Mukhtiyar Ali",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "poets": [
                {
                    "id": 26,
                    "name": "Badar Muneer",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "songTitle": {
                "id": 50,
                "originalTitle": "नित खैर मंगा",
                "englishTranslation": "Everyday I Ask For Your Well Being",
                "englishTransliteration": "Nit Khair Manga"
            }
        },
        {
            "id": 12,
            "isAuthoringComplete": true,
            "showOnLandingPage": true,
            "youtubeVideoId": "sop02Ivqji0",
            "soundCloudTrackId": "https://soundcloud.com/kashanilyas/we-main-chori-chori-tere-nal/sets",
            "thumbnailURL": "https://farm8.staticflickr.com/7511/16096481990_31028b8c63_o.jpg",
            "duration": "06:45",
            "singers": [
                {
                    "id": 12,
                    "name": "Prahlad Singh Tipanya",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "poets": [
                {
                    "id": 11,
                    "name": "Kabir",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "songTitle": {
                "id": 56,
                "originalTitle": "थारा रंगमहल में",
                "englishTranslation": "In Your Colourful Palace",
                "englishTransliteration": "Thaara Rangmahal Mein"
            }
        },
        {
            "id": 10,
            "isAuthoringComplete": true,
            "showOnLandingPage": true,
            "youtubeVideoId": "0bA18u992fw",
            "soundCloudTrackId": null,
            "thumbnailURL": "http://i.ytimg.com/vi/0bA18u992fw/mqdefault.jpg",
            "duration": "13:00",
            "singers": [
                {
                    "id": 20,
                    "name": "Mitha Khan Jat",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "poets": [
                {
                    "id": 24,
                    "name": "Shah Abdul Latif Bhitai",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],

            "songTitle": {
                "id": 54,
                "originalTitle": "सुर सोहिनी",
                "englishTranslation": "Sur Sohini",
                "englishTransliteration": "Sur Sohini"
            }
        },
        {
            "id": 15,
            "isAuthoringComplete": true,
            "showOnLandingPage": true,
            "youtubeVideoId": "fFvHSXklr9A",
            "soundCloudTrackId": null,
            "thumbnailURL": "https://farm8.staticflickr.com/7520/16097708519_a5650eab25_o.png",
            "duration": "06:54",
            "singers": [
                {
                    "id": 25,
                    "name": "Mukhtiyar Ali",
                    "hindiName": "",
                    "primaryOccupation": ""
                },
                {
                    "id": 32,
                    "name": "Kaluram Bamaniya",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "poets": [
                {
                    "id": 11,
                    "name": "Kabir",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],

            "songTitle": {
                "id": 66,
                "englishTranslation": "Meditate My Friend",
                "englishTransliteration": "Bhajo Re Bhaiya"
            }
        },
        {
            "id": 9,
            "isAuthoringComplete": true,
            "showOnLandingPage": true,
            "youtubeVideoId": "luCOKxUvOWc",
            "soundCloudTrackId": "https://soundcloud.com/kashanilyas/we-main-chori-chori-tere-nal/sets",
            "thumbnailURL": "https://farm8.staticflickr.com/7564/16203470586_6bd3f05f1f_o.png",
            "duration": "07:05",
            "singers": [
                {
                    "id": 10,
                    "name": "Mooralala Marwada",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "poets": [
                {
                    "id": 13,
                    "name": "Kabir",
                    "hindiName": "कबीर",
                    "primaryOccupation": ""
                }
            ],
            "songTitle": {
                "id": 35,
                "englishTranslation": "In This Body",
                "englishTransliteration": "Hiye Kaaya Mein"
            }
        }
    ]
};

var test_words_mainfc = {
    words: [{
        "id": 2,
        "wordOriginal": "जागना सोना",
        "wordTranslation": "Waking Sleeping",
        "wordTransliteration": "Jaagna Sona",
        "englishIntroExcerpt": "",
        "wordIntroHindi": "",
        "wordIntroEnglish": "",
        "writers": [
            {
                "id": 7,
                "name": "Parvathy Baul",
                "hindiName": "",
                "primaryOccupation": null
            }
        ],
        "displayAjabShaharTeam": false,
        "isRootWord": true
    }]
};

var test_reflections_mainfc ={
    "reflections": [
        {
            "showOnMainFcPage": true,
            "id": 5,
            "title": "reflection",
            "verb": "reflection",
            "speaker": {
                "id": 17,
                "name": "Fakru",
                "hindiName": "",
                "primaryOccupation": null
            },
            "youtubeVideoId": null,
            "publish": true
        },
        {
            "showOnMainFcPage": true,
            "id": 6,
            "title": "reflection1",
            "verb": "reflec",
            "speaker": {
                "id": 36,
                "name": "hjhj jhjkahf",
                "hindiName": "",
                "primaryOccupation": null
            },
            "publish": false
        },
        {
            "showOnMainFcPage": true,
            "id": 4,
            "title": "Unbelievable",
            "verb": null,
            "speaker": {
                "id": 14,
                "name": "Gavra Devi",
                "hindiName": "",
                "primaryOccupation": null
            },
            "youtubeVideoId": "T70-HTlKRXo",
            "publish": true
        }
    ]
};