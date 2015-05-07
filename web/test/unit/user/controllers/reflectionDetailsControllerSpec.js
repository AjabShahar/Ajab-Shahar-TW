describe("reflection details controller",function(){
    var scope,
        $route,
        $httpBackend,
        reflectionsContentService;

    beforeEach(module('reflection'));

    beforeEach(inject(function(_$controller_, _$rootScope_,_$httpBackend_,_$route_,_reflectionsContentService_){
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        $route = {current:{params:{id:1}}};
        reflectionsContentService = _reflectionsContentService_;

        _$controller_("reflectionDetailsController",{
            $scope :scope,
            $route:$route,
            reflectionsContentService:reflectionsContentService

        });
        $httpBackend.when("GET","/user/js/reflections/partials/allReflections.html").respond(200);
    }));

    it("should get the reflection details based on ID",function(){
        $httpBackend.when("GET", "/api/reflections/edit?id=1").respond(test_reflectionRepresentation);
        $httpBackend.flush();

        expect(scope.reflectionId).toBe(1);
        expect(scope.reflectionDetails.title).toBe("Poet is God says Vipul");
        expect(scope.reflectionDetails.links.length).toBe(5);
    });

});
var test_reflectionRepresentation = {
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
            "rootWord": false
        }
    ],
    "people":[
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