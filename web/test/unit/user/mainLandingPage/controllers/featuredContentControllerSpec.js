'use strict';

describe('Main landing page controller', function () {
    var scope, $httpBackend,popupService;

    beforeEach(module("featuredContentApp"));

    beforeEach(inject(function (_$controller_, _$rootScope_, _mainLandingContentService_, _popupService_,_$httpBackend_,_overviewMapperService_) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        popupService = _popupService_;

        _$controller_('featuredContentController', {
            $scope: scope,
            mainLandingContentService: _mainLandingContentService_,
            overviewMapperService:_overviewMapperService_,
            popupService: popupService
        });

        $httpBackend.when("GET", "/api/songs/getPublishedSongs").respond(test_songs_mainfc);
        $httpBackend.when("GET", "/api/reflections/completeInfo?content=featured").respond(test_reflections_mainfc);
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
            "thumbnailURL": "https://avatars1.githubusercontent.com/u/3875065?v=3&s=72",
            "info": "<p>By Vipul</p>",
            "about": null,
            "duration": "",
            "reflectionExcerpt": "This is text reflection excerpt. This is text reflection excerpt.This is text reflection excerpt",
            "id": 1,
            "title": "Poet is God",
            "verb": "says",
            "speaker": {
                "id": 16,
                "name": "Vipul Rikhi",
                "hindiName": "",
                "primaryOccupation": "Singer"
            },
            "soundCloudId": null,
            "youtubeVideoId": "MtIoD16yTQc",
            "reflectionTranscripts": [
                {
                    "id": 1,
                    "hindiTranscript": "hindi transcript",
                    "englishTranscript": "english transcript"
                }
            ],
            "words": [
                {
                    "id": 34,
                    "wordOriginal": "New word",
                    "wordTranslation": "New word",
                    "wordTransliteration": "New word",
                    "hindiIntroExcerpt": "",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": false
                }
            ],
            "songs": [
                {
                    "id": 11,
                    "englishTranslationTitle": "Everyday I Ask For Your Well Being",
                    "englishTransliterationTitle": "Nit Khair Manga",
                    "singers": [
                        {
                            "id": 25,
                            "name": "Mukhtiyar Ali",
                            "hindiName": "",
                            "primaryOccupation": null
                        }
                    ],
                    "poet": [
                        {
                            "id": 26,
                            "name": "Badar Muneer",
                            "hindiName": "",
                            "primaryOccupation": null
                        }
                    ],
                    "duration": "07:45",
                    "category": "Songs",
                    "thumbnailUrl": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png"
                }
            ],
            "publish": true,
            "people": [
                {
                    "id": 8,
                    "name": "Roshik",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ]
        },
        {
            "showOnMainFcPage": true,
            "thumbnailURL": null,
            "info": null,
            "about": null,
            "duration": null,
            "reflectionExcerpt": null,
            "id": 10,
            "title": "test reflection",
            "verb": "by",
            "speaker": {
                "id": 15,
                "name": "Shabnam Virmani",
                "hindiName": "",
                "primaryOccupation": null
            },
            "soundCloudId": "mn",
            "youtubeVideoId": null,
            "reflectionTranscripts": [],
            "words": [
                {
                    "id": 26,
                    "wordOriginal": "à¤¶à¥‚à¤¨à¥à¤¯",
                    "wordTranslation": "Emptiness",
                    "wordTransliteration": "Shoonya",
                    "hindiIntroExcerpt": "Shoonya is literally zero in the Indian numbering system, the place from which all things begin.",
                    "englishIntroExcerpt": "Hindi. Shoonya is literally zero in the Indian numbering system, the place from which all things begin.",
                    "writers": [
                        {
                            "id": 10,
                            "name": "Mooralala Marwada",
                            "hindiName": "",
                            "primaryOccupation": null
                        }
                    ],
                    "rootWord": true
                }
            ],
            "songs": [],
            "publish": true,
            "people": []
        },
        {
            "showOnMainFcPage": true,
            "thumbnailURL": null,
            "info": null,
            "about": null,
            "duration": null,
            "reflectionExcerpt": null,
            "id": 5,
            "title": "reflection",
            "verb": "says",
            "speaker": {
                "id": 17,
                "name": "Fakru",
                "hindiName": "",
                "primaryOccupation": null
            },
            "soundCloudId": null,
            "youtubeVideoId": null,
            "reflectionTranscripts": [
                {
                    "id": 2,
                    "hindiTranscript": "<p>jeu</p>",
                    "englishTranscript": "<p>heu</p>"
                }
            ],
            "words": [],
            "songs": [],
            "publish": true,
            "people": []
        }
    ]
};