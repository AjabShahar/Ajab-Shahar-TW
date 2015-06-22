describe("contentDetails model", function () {
    it("should construct a details model from word Representation", function () {
        var wordDetailsObject = new AjabShahar.DetailsObject(test_wordRepresentation, 'word');

        expect(wordDetailsObject.audioId).toBe("http://sound.com/audioId");
        expect(wordDetailsObject.videoId).toBe("MtIoD16yTQc");

        expect(wordDetailsObject.textSections).toBe(test_wordRepresentation.wordIntroduction.wordIntroEnglish);

        expect(wordDetailsObject.links.length).toBe(2);
        expect(wordDetailsObject.links[0].name).toBe("Mooralala Marwada");
        expect(wordDetailsObject.links[0].description).toBe("poet");

        expect(wordDetailsObject.verb).toBe("Introduction by");

        expect(wordDetailsObject.people.length).toBe(2);
        expect(wordDetailsObject.people[0]).toBe("Mooralala Marwada");
    });

    it("should construct reflection details object from reflection representation", function () {
        var reflectionDetailsObject = new AjabShahar.DetailsObject(test_reflectionRepresentation1, 'reflection');

        expect(reflectionDetailsObject.audioId).toBe("http://sound.com/audioId");
        expect(reflectionDetailsObject.videoId).toBe("MtIoD16yTQc");

        expect(reflectionDetailsObject.textSections).toBe(test_reflectionRepresentation1.reflectionTranscripts[0].englishTranscript);

        expect(reflectionDetailsObject.verb).toBe("says");
        expect(reflectionDetailsObject.people.length).toBe(1);
        expect(reflectionDetailsObject.people[0]).toBe("Vipul Rikhi");

        expect(reflectionDetailsObject.links.length).toBe(5);

    });

    it("should construct reflection details object from reflection representation with proper values in the link field", function () {
        reflectionDetailsObject = new AjabShahar.DetailsObject(test_reflectionRepresentation1, 'reflection');

        expect(reflectionDetailsObject.links.length).toBe(5);

        //speaker first
        expect(reflectionDetailsObject.links[0].name).toBe("Vipul Rikhi");
        expect(reflectionDetailsObject.links[0].description).toBe("writer");

        // related people next
        expect(reflectionDetailsObject.links[1].name).toBe("Gavra Devi");
        expect(reflectionDetailsObject.links[1].description).toBe("singer");

        expect(reflectionDetailsObject.links[2].name).toBe("Roshik");
        expect(reflectionDetailsObject.links[2].description).toBe("poet");

        //related words after that
        expect(reflectionDetailsObject.links[3].name).toBe("shakkar");
        expect(reflectionDetailsObject.links[3].description).toBe("WORD");
        expect(reflectionDetailsObject.links[3].alternateName).toBe("sugar");
        expect(reflectionDetailsObject.links[3].link).toBe("/words/#/details/15");

        //related songs
        expect(reflectionDetailsObject.links[4].name).toBe("Nit Khair Manga");
        expect(reflectionDetailsObject.links[4].description).toBe("SONG");
        expect(reflectionDetailsObject.links[4].link).toBe("/songs/?id=11");
    });

    it("should construct song details object from song representation ", function () {
        songDetails = new AjabShahar.DetailsObject(test_song, 'song');

        expect(songDetails.id).toBe(1);
        expect(songDetails.audioId).toBe("https://soundcloud.com/zedsdead/zeds-dead-hadouken-vip");
        expect(songDetails.videoId).toBe("tNh2kjmSzPw");
        expect(songDetails.about).toBe(null);
        expect(songDetails.downloadUrl).toBe(null);
        expect(songDetails.links.length).toBe(4);

        expect(songDetails.links[0]).toBe(null);
        expect(songDetails.links[1].name).toBe("Roshik");
        expect(songDetails.links[1].description).toBe("POET");

        expect(songDetails.links[2]).toBe(undefined);
        expect(songDetails.links[3].name).toBe("word transliteration");
        expect(songDetails.links[3].link).toBe("/words/#/details/6");
        expect(songDetails.links[3].alternateName).toBe("translation");
        expect(songDetails.links[3].description).toBe("WORD");

    });


});

describe("content details directive", function () {
    var $compile,
        scope,
        template,
        rootScope,
        $httpBackend;

    beforeEach(module('testTemplate'));
    beforeEach(module('thumbnailModule'));

    beforeEach(inject(function (_$compile_, _$rootScope_, $templateCache, _$httpBackend_) {
        $compile = _$compile_;
        scope = _$rootScope_.$new();
        template = $templateCache;
        rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
    }));


    xit("should show text content by default when it is present, in case of words", function () {
        //$httpBackend.expectGET("/user/js/common/directives/carouselSupport/carousel.html").respond('200');

        scope.detailsObject = new AjabShahar.DetailsObject(test_wordRepresentation, 'word');
        var directiveElement = angular.element('<content-details ng-if="detailsObject" content="detailsObject">');
        $compile(directiveElement)(scope);
        rootScope.$digest();

        var directiveScope = directiveElement.isolateScope();
        console.log("directive scope :", directiveScope);

        expect(scope.hasText()).toBeTruthy();
        expect(scope.showVideo).toBeFalsy();
        expect(scope.showAudio).toBeFalsy();

    });
});
var test_song = {
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
var test_wordRepresentation = {
    "id": 2,
    "wordOriginal": "जागना सोना",
    "wordTranslation": "Waking Sleeping",
    "wordTransliteration": "Jaagna Sona",
    "englishIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
    "hindiIntroExcerpt": null,
    "diacritic": null,
    "isRootWord": true,
    "showOnLandingPage": true,
    "meaning": null,
    "wordIntroduction":
        {
            "id": 83,
            "wordIntroHindi": "",
            "wordIntroEnglish": "<p>some text</p>",
            "contentType": "couplet",
            "poet": {
                "id": 17,
                "firstName": "Fakru",
                "middleName": null,
                "lastName": null,
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
                "hindiName": "",
                "name": "Fakru"
            }
        },
    "reflections": [
        {
            "id": 4,
            "title": "Unbelievable",
            "speaker": {
                "id": 14,
                "name": "Gavra Devi",
                "hindiName": "",
                "primaryOccupation": null
            },
            "published": true,
            "soundCloudId": null,
            "youtubeVideoId": "T70-HTlKRXo",
            "transcripts": []
        }
    ],
    "relatedWords": [],
    "synonyms": [],
    "writers": [
        {
            "id": 10,
            "name": "Mooralala Marwada",
            "hindiName": "",
            "primaryOccupation": "poet"
        },
        {
            "id": 7,
            "name": "Parvathy Baul",
            "hindiName": "",
            "primaryOccupation": "singer"
        }
    ],
    "people": [
        {
            "id": 8,
            "name": "Roshik",
            "hindiName": "",
            "primaryOccupation": null
        },
        {
            "id": 14,
            "name": "Gavra Devi",
            "hindiName": "",
            "primaryOccupation": null
        }
    ],
    "displayAjabShaharTeam": false,
    "defaultReflection": {
        "id": 1,
        "title": "Poet is God says Vipul",
        "speaker": {
            "id": 16,
            "name": "Vipul Rikhi",
            "hindiName": "",
            "primaryOccupation": null
        },
        "published": true,
        "soundCloudId": "http://sound.com/audioId",
        "youtubeVideoId": "MtIoD16yTQc"
    }
};

var test_reflectionRepresentation1 = {
    "showOnMainFcPage": true,
    "id": 1,
    "title": "Poet is God says Vipul",
    "verb": "says",
    "speaker": {
        "id": 16,
        "name": "Vipul Rikhi",
        "hindiName": "",
        "primaryOccupation": "writer"
    },
    "soundCloudId": "http://sound.com/audioId",
    "youtubeVideoId": "MtIoD16yTQc",
    "reflectionTranscripts": [
        {
            "id": 3,
            "hindiTranscript": "",
            "englishTranscript": "<p>That said, Žižek decries political correctness for two main reasons</p>"
        }
    ],
    "words": [
        {
            "id": 15,
            "wordOriginal": "shakkar",
            "wordTranslation": "sugar",
            "wordTransliteration": "shakkar",
            "hindiIntroExcerpt": "",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": true,
            "publish": true
        }
    ],
    "people": [
        {
            "id": 14,
            "name": "Gavra Devi",
            "hindiName": "",
            "primaryOccupation": "singer"
        },
        {
            "id": 8,
            "name": "Roshik",
            "hindiName": "",
            "primaryOccupation": "poet"
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
    "publish": true
};
