describe("contentDetails model", function () {
    it("should construct a details model from word Representation", function () {
        var wordDetailsObject = new AjabShahar.DetailsObject(test_wordRepresentation, 'word');

        expect(wordDetailsObject.audioId).toBe("http://sound.com/audioId");
        expect(wordDetailsObject.videoId).toBe("MtIoD16yTQc");

        expect(wordDetailsObject.textSections.length).toBe(3);
        expect(wordDetailsObject.textSections[0].text).toBe(test_wordRepresentation.wordIntroductions[0].wordIntroEnglish);
        expect(wordDetailsObject.textSections[0].type).toBe('couplet');

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

        expect(reflectionDetailsObject.textSections.length).toBe(3);
        expect(reflectionDetailsObject.textSections[1].text).toBe(test_reflectionRepresentation1.reflectionTranscripts[1].englishTranscript);

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
    "wordIntroductions": [
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
        {
            "id": 84,
            "wordIntroHindi": "",
            "wordIntroEnglish": "<p>Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...</p>",
            "contentType": "text",
            "poet": null
        },
        {
            "id": 85,
            "wordIntroHindi": "",
            "wordIntroEnglish": "<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>",
            "contentType": "text",
            "poet": null
        }
    ],
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
            "id": 1,
            "hindiTranscript": null,
            "englishTranscript": null
        },
        {
            "id": 3,
            "hindiTranscript": "",
            "englishTranscript": "<p>That said, Žižek decries political correctness for two main reasons</p>"
        },
        {
            "id": 4,
            "hindiTranscript": "",
            "englishTranscript": "<p>In fact these future machines will be even more humanlike than humans today</p>"
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