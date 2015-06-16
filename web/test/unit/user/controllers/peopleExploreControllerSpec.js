describe("people explore controller", function () {

    var httpBackend, scope;

    beforeEach(module("people"));

    beforeEach(inject(function (_$rootScope_, _$httpBackend_, peopleService, _$controller_) {
        scope = _$rootScope_.$new();
        httpBackend = _$httpBackend_;

        _$controller_('peopleExploreController', {
            $scope: scope,
            $route: {
                current: {params: {id: 1}}
            },
            peopleService: peopleService
        });

        httpBackend.when('GET', '/api/people/1').respond(person);
        httpBackend.when('GET','/api/songs?personId=1').respond(songs);
        httpBackend.when('GET','/api/reflections/summaries?personId=1').respond(reflections);
        httpBackend.when('GET','/api/words/summary?personId=1').respond(words);

    }));

    it("should get all related content for person", function () {

        httpBackend.flush();

        expect(scope.person).toBeDefined();
        expect(scope.relatedData.length).toBe(3);

    });

    it("should construct the corresponding objects",function(){
        httpBackend.flush();

        expect(scope.relatedData[0].englishTitle).toBe('In This Body');
        expect(scope.relatedData[0].type).toBe('song');
        expect(scope.relatedData[1].englishTitle).toBe('first reflection');
        expect(scope.relatedData[1].type).toBe('reflection');
        expect(scope.relatedData[2].englishTitle).toBe('translation of first word');
        expect(scope.relatedData[2].type).toBe('word');

    });

    var person = {
        "id": 1,
        "firstName": "Parvathy",
        "middleName": "",
        "lastName": "Baul",
        "firstNameInHindi": null,
        "middleNameInHindi": null,
        "lastNameInHindi": null,
        "roles": [
            "Poet"
        ],
        "primaryOccupation": null,
        "thumbnailURL": null,
        "profile": null,
        "publish": true
    };
    var songs = {
        "songs": [
            {
                "id": 7,
                "englishTranslationTitle": "In This Body",
                "englishTransliterationTitle": "Hiye Kaaya Mein",
                "singers": [
                    {
                        "publish": true,
                        "id": 6,
                        "name": "Kabir",
                        "hindiName": "",
                        "primaryOccupation": "Poet"
                    }
                ],
                "poet": [
                    {
                        "publish": true,
                        "id": 1,
                        "name": "Parvathy Baul",
                        "hindiName": "",
                        "primaryOccupation": ""
                    }
                ],
                "duration": "7:05",
                "category": "Songs",
                "thumbnailUrl": "http://mountainshepherds.com/wp-content/gallery/archival/gauradevi.jpg",
                "publish": true,
                "contentFormat": "video"
            }
        ]
    };

    var reflections = [
        {
            "thumbnailURL": "/images/chairman.jpg",
            "id": 1,
            "title": "first reflection",
            "speaker": {
                "publish": true,
                "id": 1,
                "name": "Kabir",
                "hindiName": "",
                "primaryOccupation": "Poet"
            },
            "published": true,
            "excerpt": "this excerpt",
            "duration": "10:00",
            "verb": "by",
            "contentType": "video"
        }
    ];

    var words = [
        {
            "id": 1,
            "wordOriginal": "word original",
            "wordTranslation": "translation of first word",
            "wordTransliteration": "first word",
            "hindiIntroExcerpt": "intro excerpt english",
            "englishIntroExcerpt": "",
            "writers": [
                {
                    "publish": true,
                    "id": 1,
                    "name": "Parvathy Baul",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "rootWord": true,
            "publish": true
        }
    ]

});