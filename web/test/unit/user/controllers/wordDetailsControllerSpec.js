'use strict';

describe('Word details controller', function() {
    var scope,
        $route,
        wordService,
        $location,
        $httpBackend;

    beforeEach(module('word'));

    beforeEach(inject(function (_$controller_, _$rootScope_,_$httpBackend_,_$route_,_wordService_,_$location_) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        $route = {current:{params:{id:3}}};
        wordService =_wordService_;
        $location = _$location_;

        //spyOn($location, 'search').andReturn({id: 1});

        _$controller_('wordDetailsController', {
            $scope: scope,
            wordService: wordService,
            $route:$route
        });

        $httpBackend.when("GET", "/api/words/edit?id=3&publish=true").respond(test_wordDetail);
        $httpBackend.when("GET", "/user/js/words/partials/featuredContent.html").respond(200);

    }));

    it("should create the list of thumbnail objects to be displayed on carousel",function(){
        $httpBackend.flush();

        expect(scope.carouselItems.length).toBe(3);

    });

    it("should display only the published reflections that are related to the word",function(){
        $httpBackend.flush();

        expect(scope.carouselItems.length).toBe(3);

        expect(scope.carouselItems[0].type).toBe('word');

        expect(scope.carouselItems[1].type).toBe('reflection');
        expect(scope.carouselItems[1].id).toBe(1);

        expect(scope.carouselItems[2].type).toBe('reflection');
        expect(scope.carouselItems[2].id).toBe(4);

    });

});

var test_wordDetail ={
    "id": 3,
    "wordOriginal": "अकथ कथा",
    "wordTranslation": "Untellable Tale",
    "wordTransliteration": "Akath Katha",
    "englishIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told? Perhaps because there is an enticing, irresistible vastness and mysteriousness to the unspeakable story. It is not mystification but an invitation to enter… a realm of experience beyond description.",
    "hindiIntroExcerpt": null,
    "diacritic": null,
    "isRootWord": true,
    "showOnLandingPage": true,
    "meaning": null,
    "wordIntroductions": [
        {
            "id": 82,
            "wordIntroHindi": "",
            "wordIntroEnglish": "<p>sfsdfdfer jie bpawe ojasd moiald twisew  dserfs</p>",
            "contentType": "text",
            "poet": null
        },
        {
            "id": 81,
            "wordIntroHindi": "",
            "wordIntroEnglish": "<p>some text hosfdo ijiojeq dsfije oernf opawed</p>",
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
                "primaryOccupation": ""
            },
            "published": true
        },
        {
            "id": 7,
            "title": "reflection",
            "speaker": {
                "id": 0,
                "name": null,
                "hindiName": null,
                "primaryOccupation": null
            },
            "published": false
        }
    ],
    "relatedWords": [],
    "songs": [],
    "synonyms": [],
    "writers": [],
    "people": [],
    "displayAjabShaharTeam": false,
    "defaultReflection": {
        "id": 1,
        "title": "Poet is God says Vipul",
        "speaker": {
            "id": 16,
            "name": "Vipul Rikhi",
            "hindiName": "",
            "primaryOccupation": ""
        },
        "published": true
    }
}