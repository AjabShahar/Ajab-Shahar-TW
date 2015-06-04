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

        _$controller_('reflectionDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: $location,
            reflectionContentService: reflectionContentService,
            loginVerifyService: loginVerifyService
        });

        httpBackend.expectGET('/api/people/summary?show=all').respond(200, ['people data']);
        httpBackend.expectGET('/api/words/summary').respond(200, test_admin_wordSummary);
        httpBackend.expectGET('/api/songs').respond(song_summary);
    }));

    describe("should test save of reflection", function () {
        it("after saving should redirect to admin page", function () {
            httpBackend.flush();
            httpBackend.when('POST','/api/reflections').respond(200);

            scope.saveData();
            httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });

        it("should append /images/ for thumbnail url,if it is just filename", function () {
            scope.reflection.thumbnailURL = "thumbnail.jpg";
            httpBackend.when('POST','/api/reflections').respond(200);

            scope.saveData();
            httpBackend.flush();

            expect(scope.reflection.thumbnailURL).toBe('/images/thumbnail.jpg');
        });
        it("should not append /images/ for thumbnail url,if it already have /images appended in filename", function () {
            scope.reflection.thumbnailURL = "/images/thumbnail.jpg";
            httpBackend.when('POST','/api/reflections').respond(200);

            scope.saveData();
            httpBackend.flush();

            expect(scope.reflection.thumbnailURL).toBe('/images/thumbnail.jpg');
        });
        it("should not append /images/ for thumbnail url,if the image is from internet source", function () {
            scope.reflection.thumbnailURL = "http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg";
            httpBackend.when('POST','/api/reflections').respond(200);

            scope.saveData();
            httpBackend.flush();

            expect(scope.reflection.thumbnailURL).toBe('http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg');
        });
        it("then should have singers as a comma separated entries for a song", function () {

            httpBackend.flush();

            expect(scope.songs[0].menuTitle).toBe('Bhajan - (Gavra Devi)');
            expect(scope.songs[2].menuTitle).toBe('Kichhu din mone mone - (Parvathy Baul)');
        });
        it("then shouldn't have singers as a comma separated entries for a song, if there are no singers", function () {

            httpBackend.flush();

            expect(scope.songs[1].menuTitle).toBe('Hiye Kaaya Mein');
        });
    });

    describe("Should remove other reflection types and their information when one of the types is selected", function () {

        it("When reflection type is audio, should have other types as null", function () {
            httpBackend.flush();
            httpBackend.when('POST','/api/reflections').respond(200, {'id': 1});

            scope.saveData();
            httpBackend.flush();

            //expect(scope.reflection.type).toBe('video');

            scope.reflection.type = "audio";
            scope.reflection.soundCloudId = "soundCloudURL";
            scope.reflection.about = "about";

            //scope.saveData();

            expect(scope.reflection.about).toBe("about");
            expect(scope.reflection.youtubeVideoId).toBe(null);
            expect(scope.reflection.soundCloudId).toBe("soundCloudURL");
        });
        it("When reflection type is audio, should have other types as null", function () {
            httpBackend.flush();
            httpBackend.when('POST','/api/reflections').respond(200, {'id': 1});

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
        });
    });
    describe("should test updating of reflection", function () {
        it("after updating should redirect to admin page", function () {
            spyOn($location, 'search').andReturn({id: 1});
            httpBackend.when('GET','/api/reflections/edit?id=1&publish=false').respond(test_admin_reflection);

            httpBackend.flush();
            httpBackend.when('POST','/api/reflections').respond(200, {'id': 1});

            scope.saveData();
            httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });
        it("should show already linked words for the reflection", function () {
            spyOn($location, 'search').andReturn({id: 1});
            httpBackend.when('GET','/api/reflections/edit?id=1&publish=false').respond(test_admin_reflection);

            httpBackend.flush();

            expect(scope.words[1].ticked).toBeFalsy();
        });
    });

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
            "hindiTranscript": null,
            "englishTranscript": null
        }
    ],
    "words": [
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
};
var song_summary = {
    "songs": [
        {
            "id": 1,
            "englishTranslationTitle": "For a few days,O Heart",
            "englishTransliterationTitle": "Kichhu din mone mone",
            "singers": [
                {
                    "id": 1,
                    "name": "Parvathy Baul",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "poet": [
                {
                    "id": 2,
                    "name": "Roshik",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "duration": "5:45",
            "category": "Songs",
            "thumbnailUrl": "http://3.bp.blogspot.com/-kwpgiMcXc24/TcOcowo6mTI/AAAAAAAAA9w/uNt6ZsJadDg/s1600/parvathy_baul03.jpg",
            "publish": true
        },
        {
            "id": 2,
            "englishTranslationTitle": "The Cart of Meditation is Tottering",
            "englishTransliterationTitle": "Bhajan",
            "singers": [
                {
                    "id": 3,
                    "name": "Gavra Devi",
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
            "publish": true
        },
        {
            "id": 5,
            "englishTranslationTitle": "In This Body",
            "englishTransliterationTitle": "Hiye Kaaya Mein",
            "singers": [],
            "poet": [],
            "duration": "7:05",
            "category": "Songs",
            "thumbnailUrl": "http://mountainshepherds.com/wp-content/gallery/archival/gauradevi.jpg",
            "publish": true
        }
    ]
};