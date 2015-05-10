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

        $httpBackend.when("GET", "/api/songs/content=featured").respond(test_songs_mainfc);
        $httpBackend.when("GET", "/api/reflections?content=featured").respond(test_reflections_mainfc);
        $httpBackend.when("GET", "/api/words?showOnMainLandingPage=true").respond(test_words_mainfc);

    }));


    xit("should construct the content overviews list with the same order of elements as the elements in thumbnail objects list ", function () {

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

var test_songs_mainfc={
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
            "thumbnailUrl": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png",
            "published": true,
            "contentFormat": "video"
        },
        {
            "id": 17,
            "englishTranslationTitle": "In This Body",
            "englishTransliterationTitle": "Hiye Kaaya Mein",
            "singers": [],
            "poet": [],
            "duration": null,
            "category": "Songs",
            "thumbnailUrl": null,
            "published": false,
            "contentFormat": "audio"
        }]
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