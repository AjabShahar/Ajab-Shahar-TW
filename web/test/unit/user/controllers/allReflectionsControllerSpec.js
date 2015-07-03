describe("all reflections controller", function () {

    var reflectionsContentService,
        scope,
        fakeWindow,
        $window;

    beforeEach(module("reflection"));

    beforeEach(inject(function (_$controller_, _$rootScope_, _$httpBackend_, _$window_, _reflectionsContentService_,_$location_) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        reflectionsContentService = _reflectionsContentService_;
        fakeWindow = {location: {href: ''}};

        $location = _$location_;
        _$controller_("allReflectionsController", {
            $scope: scope,
            $window: fakeWindow,
            reflectionsContentService: reflectionsContentService
        });
        $httpBackend.when("GET", "/api/reflections/completeInfo?content=authoringComplete").respond(test_reflections);

    }));

    it("should get all published reflections", function () {

        $httpBackend.flush();

        expect(scope.reflectionsList.length).toBe(2);
    });

    it("should update reflections count", function () {

        $httpBackend.flush();

        expect(scope.reflectionCount).toBe(2);
    });

    it("should sort reflections based on title", function () {

        $httpBackend.flush();

        expect(scope.reflectionsList[0].englishTitle).toBe("oh that word!");
        expect(scope.reflectionsList[1].englishTitle).toBe("Poet is God");
    });

    it("should get thumbnail representation of reflections", function () {

        $httpBackend.flush();

        expect(scope.reflectionsList[0].type).toBe("reflection");
        expect(scope.reflectionsList[1].type).toBe("reflection");

        expect(scope.reflectionsList[0].id).toBe(9);
        expect(scope.reflectionsList[0].thumbnailImg).toBe("https://avatars2.githubusercontent.com/u/11533144?v=3&s=72");
        expect(scope.reflectionsList[0].description).toBe("hey this is excerpt");
        expect(scope.reflectionsList[0].verbPeople.verb).toBe("says");
        expect(scope.reflectionsList[0].verbPeople.people).toBe("Parvathy Baul");
        expect(scope.reflectionsList[0].englishTitle).toBe("oh that word!");
        expect(scope.reflectionsList[0].contentCategory).toBe("reflection");
        expect(scope.reflectionsList[0].duration).toBe("10:00");
        expect(scope.reflectionsList[0].contentFormat).toBe("video");

        expect(scope.reflectionsList[1].contentFormat).toBe("audio");
    });

    it("should redirect to details page on clicking of reflection thumbnail", function () {
        scope.navigateToDetailPage(test_reflections.reflections[1]);

        expect(fakeWindow.location.href).toBe('/reflections/details/1/Poet-is-God');
    });
});

var test_reflections = {
    reflections: [
        {
            "showOnMainFcPage": true,
            "thumbnailURL": "https://avatars2.githubusercontent.com/u/11533144?v=3&s=72",
            "info": null,
            "about": "<p>ghsjdgLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,Â </p>",
            "duration": "10:00",
            "reflectionExcerpt": "hey this is excerpt",
            "id": 9,
            "title": "oh that word!",
            "verb": "says",
            "speaker": {
                "id": 7,
                "publish": true,
                "name": "Parvathy Baul",
                "hindiName": "",
                "primaryOccupation": "Singer"
            },
            "soundCloudId": null,
            "youtubeVideoId": "qJks37maCrs",
            "reflectionTranscripts": [],
            "words": [
                {
                    "id": 2,
                    "wordOriginal": "à¤œà¤¾à¤—à¤¨à¤¾ à¤¸à¥‹à¤¨à¤¾",
                    "wordTranslation": "Waking Sleeping",
                    "wordTransliteration": "Jaagna Sona",
                    "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: â€˜So, are you enlightened now?â€™ The Buddhaâ€™s response was: â€˜I am awake.â€™",
                    "englishIntroExcerpt": "",
                    "writers": [
                        {
                            "id": 16,
                            "name": "Vipul Rikhi",
                            "hindiName": "",
                            "primaryOccupation": "Singer"
                        }
                    ],
                    "rootWord": true
                }
            ],
            "songs": [
                {
                    "id": 17,
                    "englishTranslationTitle": "In This Body",
                    "englishTransliterationTitle": "Hiye Kaaya Mein",
                    "singers": [],
                    "poet": [],
                    "duration": null,
                    "category": "Songs",
                    "thumbnailUrl": null
                }
            ],
            "publish": true,
            "people": []
        },
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
                "publish": true,
                "name": "Vipul Rikhi",
                "hindiName": "",
                "primaryOccupation": "Singer"
            },
            "soundCloudId": "MtIoD16yTQc",
            "youtubeVideoId": null,
            "reflectionTranscripts": [
                {
                    "id": 1,
                    "hindiTranscript": null,
                    "englishTranscript": null
                }
            ],
            "words": [
                {
                    "id": 27,
                    "wordOriginal": "kokoko",
                    "wordTranslation": "ktktkt",
                    "wordTransliteration": "ktlktlktl",
                    "hindiIntroExcerpt": "Excerpt text Excerpt text Excerpt text",
                    "englishIntroExcerpt": "hindi. Excerpt text Excerpt text Excerpt text",
                    "writers": [
                        {
                            "id": 7,
                            "name": "Parvathy Baul",
                            "hindiName": "",
                            "primaryOccupation": "Singer"
                        }
                    ],
                    "rootWord": true
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
        }
    ]
};