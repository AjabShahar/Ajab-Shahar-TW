'use strict';
describe("reflection details controller", function () {

    var scope, $location, fakeWindow, httpBackend;

    beforeEach(module('reflectionsAdminApp'));

    beforeEach(inject(function (_$controller_, $rootScope, _$window_, _$location_, _$httpBackend_, reflectionContentService, loginVerifyService, $cookies) {
        scope = $rootScope.$new();
        $location = _$location_;
        httpBackend = _$httpBackend_;
        fakeWindow = {location: {href: ''}};
        $cookies.user = "admin";

        spyOn($location, 'search').andReturn({id:1});


        _$controller_('reflectionDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: _$location_,
            reflectionContentService: reflectionContentService,
            loginVerifyService: loginVerifyService
        });

        httpBackend.expectGET('/api/people/summary').respond(200, ['people data']);
        httpBackend.expectGET('/api/words/summary').respond(200, test_admin_wordSummary);
        httpBackend.expectGET('/api/songs/getAllSongs').respond(200, {data: null});
        httpBackend.expectGET('/api/reflections/edit?id=1').respond(200,test_admin_reflection);
    }));

    describe("should test save of reflection", function () {
        it("after saving should redirect to admin page", function () {
            httpBackend.flush();
            httpBackend.expectPOST('/api/reflections').respond(200);

            scope.saveData();
            httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });
    });

    describe("Should remove other reflection types and their information when one of the types is selected", function(){
       it("When reflection type is audio, should have other types as null", function(){
           httpBackend.flush();
           httpBackend.expectPOST('/api/reflections').respond(200, {'id': 1});

           scope.saveData();
           httpBackend.flush();

           expect(scope.reflection.type).toBe('video');

           scope.reflection.type = "audio";
           scope.reflection.soundCloudId = "soundCloudURL";

           scope.saveData();

           expect(scope.reflection.about).toBe("about");
           expect(scope.reflection.youtubeVideoId).toBe(null);
           expect(scope.reflection.soundCloudId).toBe("soundCloudURL");
       }) ;
        it("When reflection type is audio, should have other types as null", function(){
            httpBackend.flush();
            httpBackend.expectPOST('/api/reflections').respond(200, {'id': 1});

            scope.saveData();
            httpBackend.flush();

            scope.reflection.type = "text";
            scope.reflection.info = "info";
            scope.saveData();

            expect(scope.reflection.about).toBe(null);
            expect(scope.reflection.info).toBe("info");
            expect(scope.reflection.youtubeVideoId).toBe(null);
            expect(scope.reflection.soundCloudId).toBe(null);
            expect(scope.reflection.type).toBe("text");
        }) ;
    });

    describe("should test updating of reflection", function () {
        it("after updating should redirect to admin page", function () {
            httpBackend.flush();
            httpBackend.expectPOST('/api/reflections').respond(200, {'id': 1});

            scope.saveData();
            httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });
    });

    it("should show already linked words for the reflection",function(){
        httpBackend.flush();

        expect(scope.words[0].ticked).toBeTruthy();
        expect(scope.words[2].ticked).toBeTruthy();

    })
});


var test_admin_wordSummary = [
        {
            "id": 1,
            "wordOriginal": "अकथ कथा",
            "wordTranslation": "Akath Katha",
            "wordTransliteration": "Untellable Tale",
            "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told?",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": false
        },
        {
            "id": 2,
            "wordOriginal": "जागना सोना",
            "wordTranslation": "Waking Sleeping",
            "wordTransliteration": "Jaagna Sona",
            "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": true
        },
        {
            "id": 3,
            "wordOriginal": "अकथ कथा",
            "wordTranslation": "Untellable Tale",
            "wordTransliteration": "Akath Katha",
            "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told? Perhaps because there is an enticing, irresistible vastness and mysteriousness to the unspeakable story. It is not mystification but an invitation to enter… a realm of experience beyond description.",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": false
        },
        {
            "id": 4,
            "wordOriginal": "शून्य",
            "wordTranslation": "Emptiness",
            "wordTransliteration": "Shoonya",
            "hindiIntroExcerpt": "Shoonya is literally zero in the Indian numbering system, the place from which all things begin. It is also a technical term in Buddhism, usually translated as emptiness or nothingness. In Sanskrit, one of its primary meanings is empty space, or void.",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": false
        }
];

var test_admin_reflection = {
    "showOnMainFcPage": true,
    "id": 1,
    "title": "Poet is God says Vipul",
    "verb": "by",
    "speaker": {
        "id": 16,
        "name": "Vipul Rikhi",
        "hindiName": ""
    },
    "soundCloudId": null,
    "youtubeVideoId": "MtIoD16yTQc",
    "transcripts": [
        {
            "id": 1,
            "hindiTranscript": null,
            "englishTranscript": null
        }
    ],
    "about": "about",
    "words": [
        {
            "id": 3,
            "wordOriginal": "अकथ कथा",
            "wordTranslation": "Untellable Tale",
            "wordTransliteration": "Akath Katha",
            "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told? Perhaps because there is an enticing, irresistible vastness and mysteriousness to the unspeakable story. It is not mystification but an invitation to enter… a realm of experience beyond description.",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": false
        },
        {
            "id": 1,
            "wordOriginal": "अकथ कथा",
            "wordTranslation": "Akath Katha",
            "wordTransliteration": "Untellable Tale",
            "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told?",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": false
        }
    ],
    "publish": true
};