'use strict';

describe("song details controller", function () {

    var httpBackend, scope, $location;
    beforeEach(module("songDetailsApp"));

    beforeEach(inject(function (_$httpBackend_, _$location_, _$rootScope_, _$controller_, _songsContentService_) {
        httpBackend = _$httpBackend_;
        scope = _$rootScope_.$new();
        $location = _$location_;

        _$controller_("songDetailsController", {
            $scope: scope,
            $location: $location,
            songsContentService: _songsContentService_
        });
        spyOn($location, 'search').andReturn({id: 1});
        httpBackend.when('GET', '/api/songs/versions?id=' + scope.songId).respond(song_summaries);
        httpBackend.flush();
    }));
    it("should get versions of song and build thumbnail objects for versions", function () {

        expect(scope.numberOfVersions).toBe(2);
    });
    it("should have the selected song in light box as first song in carousel items", function () {
        expect(scope.carouselItems[0].id).toBe(1);
        expect(scope.carouselItems[0].englishTitle).toBe("The Cart of Meditation is Tottering");
        expect(scope.carouselItems[0].verbPeople.verb).toBe("SING");
        expect(scope.carouselItems[0].verbPeople.people).toBe("Fakru & Mooralala Marwada");
        expect(scope.carouselItems[1].englishTitle).toBe("In This Body");
    });
    it("should have complete details for selected song", function () {
        scope.selectThumbnail(scope.carouselItems[0]);
        httpBackend.when('GET', '/api/songs/getPublishedSongs/' + scope.carouselItems[0].id).respond(song);
        httpBackend.flush();

        expect(scope.detailsObject.id).toBe(1);
        expect(scope.detailsObject.audioId).toBe("https://soundcloud.com/zedsdead/zeds-dead-hadouken-vip");
        expect(scope.detailsObject.videoId).toBe("tNh2kjmSzPw");
        expect(scope.detailsObject.links.length).toBe(4);
    });
    it("should have main title for selected song", function () {
        scope.selectThumbnail(scope.carouselItems[0]);
        httpBackend.when('GET', '/api/songs/getPublishedSongs/' + scope.carouselItems[0].id).respond(song);
        httpBackend.flush();

        expect(scope.mainTitle.translation).toBe("New one");
        expect(scope.mainTitle.transliteration).toBe("New Umbrella title");
    });
    it("should extract song lyrics for selected song", function () {
        scope.selectThumbnail(scope.carouselItems[0]);
        httpBackend.when('GET', '/api/songs/getPublishedSongs/' + scope.carouselItems[0].id).respond(song);
        httpBackend.flush();

        expect(scope.songText.openingCouplets.length).toBe(1);
        expect(scope.songText.songTextContents.length).toBe(2);

        expect(scope.hasSongText(song)).toBeTruthy();

    });
    it("should be able to select three types of songLyrics", function () {
        expect(scope.englishTransliterationVisible).toBeTruthy();

        scope.showEnglishTranslation();
        expect(scope.englishTranslationVisible).toBeTruthy();
        expect(scope.englishTransliterationVisible).toBeFalsy();
        expect(scope.originalVisible).toBeFalsy();

        scope.showEnglishTransliteration();
        expect(scope.englishTranslationVisible).toBeFalsy();
        expect(scope.englishTransliterationVisible).toBeTruthy();
        expect(scope.originalVisible).toBeFalsy();

        scope.showOriginal();
        expect(scope.englishTranslationVisible).toBeFalsy();
        expect(scope.englishTransliterationVisible).toBeFalsy();
        expect(scope.originalVisible).toBeTruthy();
    });

    var song = {
        "id": 1,
        "isAuthoringComplete": true,
        "showOnLandingPage": true,
        "youtubeVideoId": "tNh2kjmSzPw",
        "soundCloudTrackId": "https://soundcloud.com/zedsdead/zeds-dead-hadouken-vip",
        "thumbnailURL": "http://3.bp.blogspot.com/-kwpgiMcXc24/TcOcowo6mTI/AAAAAAAAA9w/uNt6ZsJadDg/s1600/parvathy_baul03.jpg",
        "duration": "5:45",
        "singers": [],
        "poets": [
            {
                "id": 2,
                "name": "Roshik",
                "hindiName": "",
                "primaryOccupation": ""
            }
        ],
        "links": [
            {
                "rel": "poet",
                "url": "/api/people/2"
            }
        ],
        "words": [
            {
                "id": 1,
                "wordOriginal": "word original",
                "wordTranslation": "translation of first word",
                "wordTransliteration": "first word",
                "hindiIntroExcerpt": "intro excerpt english",
                "englishIntroExcerpt": "",
                "writers": [
                    {
                        "id": 1,
                        "name": "Parvathy Baul",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "rootWord": false,
                "publish": true
            },
            {
                "id": 6,
                "wordOriginal": "second word",
                "wordTranslation": "translation",
                "wordTransliteration": "word transliteration",
                "hindiIntroExcerpt": "",
                "englishIntroExcerpt": "",
                "writers": [],
                "rootWord": true,
                "publish": true
            }
        ],
        "songText": {
            "id": 3,
            "songTextContents": [
                {
                    "id": 2,
                    "originalText": "à¤®à¤¸à¥à¤œà¤¿à¤¦ à¥™à¥à¤¦à¤¾ à¤•à¤¾ à¤˜à¤° à¤¹à¥ˆ, à¤ªà¥€à¤¨à¥‡ à¤•à¥€ à¤œà¤—à¤¹ à¤¨à¤¹à¥€à¤‚ ,\nà¤•à¤¾à¤«à¤¿à¤° à¤•à¥‡ à¤¦à¤¿à¤² à¤®à¥‡à¤‚ à¤œà¤¾ à¤µà¤¹à¤¾à¤ à¥™à¥à¤¦à¤¾ à¤¨à¤¹à¥€à¤‚ .",
                    "englishTranslationText": "Mosque is the place of God, not a place to drink,\nGo to the heart of a kafir, God is absent there.\n(In Islam Kafir refers to a person who rejects God )",
                    "englishTransliterationText": "Masjid khuda ka ghar h, pine ki jagah nahi,\nKafir k dil me ja waha khuda nahi.",
                    "contentType": "couplet",
                    "sequenceNumber": 1,
                    "showRefrain": true,
                    "poet": {
                        "id": 2,
                        "firstName": "Roshik",
                        "middleName": "",
                        "lastName": "",
                        "firstNameInHindi": null,
                        "middleNameInHindi": null,
                        "lastNameInHindi": null,
                        "category": [
                            {
                                "id": 9,
                                "name": "Poet",
                                "categoryType": "person"
                            }
                        ],
                        "primaryOccupation": {
                            "id": 0,
                            "name": null,
                            "categoryType": null
                        },
                        "thumbnailURL": null,
                        "profile": null,
                        "hindiName": "",
                        "name": "Roshik"
                    }
                },
                {
                    "id": 1,
                    "originalText": "à¤¶à¤°à¤¾à¤¬ à¤ªà¥€à¤¨à¥‡ à¤¦à¥‡ à¤®à¤¸à¥à¤œà¤¿à¤¦ à¤®à¥‡à¤‚ à¤¬à¥ˆà¤  à¤•à¤° ,\nà¤¯à¤¾ à¤µà¥‹ à¤œà¤—à¤¹ à¤¬à¤¤à¤¾ à¤œà¤¹à¤¾à¤ à¥™à¥à¤¦à¤¾ à¤¨à¤¹à¥€à¤‚",
                    "englishTranslationText": "Let me drink in the mosque,\nOr tell me the place where God is not present.",
                    "englishTransliterationText": "Sharab pine de masjid me baith kar,\nYa wo jagah bata jaha khuda nahi.",
                    "contentType": "stanza",
                    "sequenceNumber": 0,
                    "showRefrain": true,
                    "poet": null
                }
            ],
            "openingCouplets": [
                {
                    "id": 1,
                    "originalText": "Where-e'er you find \"the cooling western breeze,\"\nIn the next line, it \"whispers through the trees;\"\nIf crystal streams \"with pleasing murmurs creep,\"\nThe reader's threatened (not in vain) with \"sleep.\"",
                    "englishTranslationText": "Nor shall Death brag thou wander'st in his shade,\nWhen in eternal lines to time thou growest:\nSo long as men can breathe or eyes can see,\nSo long lives this and this gives life to thee.",
                    "englishTransliterationText": "Shall I compare thee to a summer's day?\nThou art more lovely and more temperate:\nRough winds do shake the darling buds of May,\nAnd summer's lease hath all too short a date:\nSometimes too hot the eye of heaven shines,\nAnd often is his gold complexion dimm'd;",
                    "contentType": "couplet",
                    "sequenceNumber": 0,
                    "poet": {
                        "id": 1,
                        "firstName": "Parvathy",
                        "middleName": "",
                        "lastName": "Baul",
                        "firstNameInHindi": null,
                        "middleNameInHindi": null,
                        "lastNameInHindi": null,
                        "category": [
                            {
                                "id": 9,
                                "name": "Poet",
                                "categoryType": "person"
                            }
                        ],
                        "primaryOccupation": {
                            "id": 0,
                            "name": null,
                            "categoryType": null
                        },
                        "thumbnailURL": null,
                        "profile": null,
                        "hindiName": "",
                        "name": "Parvathy Baul"
                    }
                }
            ],
            "refrainOriginal": "à¤•à¤¾à¤«à¤¿à¤° à¤•à¥‡ à¤¦à¤¿à¤²",
            "refrainEnglishTranslation": "I have looked into the heart of a Kafir",
            "refrainEnglishTransliteration": "Kafir k dil se"
        },
        "downloadURL": null,
        "about": null,
        "umbrellaTitle": {
            "id": 4,
            "originalTitle": "New Umbrella title",
            "englishTranslation": "New one",
            "englishTransliteration": "New Umbrella title",
            "category": {
                "id": 8,
                "name": "Umbrella Title",
                "categoryType": "Umbrella Title"
            }
        },
        "songTitle": {
            "id": 1,
            "originalTitle": "à¤•à¤¿à¤›à¥ à¤¦à¤¿à¤¨ à¤®à¥‹à¤¨à¥‡ à¤®à¥‹à¤¨à¥‡",
            "englishTranslation": "For a few days,O Heart",
            "englishTransliteration": "Kichhu din mone mone",
            "category": {
                "id": 7,
                "name": "Song Title",
                "categoryType": "Song Title"
            }
        },
        "gathering": null,
        "songCategory": {
            "id": 1,
            "name": "Songs",
            "categoryType": "song"
        },
        "mediaCategory": null,
        "songGenre": [],
        "reflections": [
            {
                "id": 1,
                "title": "first reflection",
                "speaker": {
                    "id": 3,
                    "name": "Gavra Devi",
                    "hindiName": "",
                    "primaryOccupation": null
                },
                "published": true,
                "thumbnailImg": "/images/chairman.jpg",
                "excerpt": "this excerpt",
                "duration": "10:00",
                "verb": "by",
                "contentType": "video"
            }
        ]
    };
    var song_summaries = {
        "songs": [
            {
                "id": 1,
                "englishTranslationTitle": "The Cart of Meditation is Tottering",
                "englishTransliterationTitle": "Bhajan Ro Guá¸ak Rahyo Gaaá¸o",
                "singers": [
                    {
                        "id": 4,
                        "name": "Fakru",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    {
                        "id": 5,
                        "name": "Mooralala Marwada",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "poet": [
                    {
                        "id": 4,
                        "name": "Fakru",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "duration": "5:10",
                "category": "Songs",
                "thumbnailUrl": "http://i.ytimg.com/vi/J4IU5tDlD_s/mqdefault.jpg",
                "publish": true,
                "contentFormat": "video"
            },
            {
                "id": 6,
                "englishTranslationTitle": "In This Body",
                "englishTransliterationTitle": "Hiye Kaaya Mein",
                "singers": [],
                "poet": [],
                "duration": "7:05",
                "category": "Songs",
                "thumbnailUrl": "http://mountainshepherds.com/wp-content/gallery/archival/gauradevi.jpg",
                "publish": true,
                "contentFormat": "video"
            }
        ]

    }

});