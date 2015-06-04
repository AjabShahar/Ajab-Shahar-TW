'use strict';

describe("Word details controller spec:", function () {
    var scope,
        fakeWindow,
        $location,
        $httpBackend,
        adminHomePage;

    beforeEach(module('wordsAdminApp'));

    beforeEach(inject(function (_$controller_, _$rootScope_, _$window_, _$location_, contentService, PAGES, _$httpBackend_, loginVerifyService, $cookies) {
        scope = _$rootScope_.$new();
        fakeWindow = {location: {href: ''}};
        $location = _$location_;
        $httpBackend = _$httpBackend_;
        $cookies.user = "admin";


        _$controller_('wordDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: $location,
            contentService: contentService,
            PAGES: PAGES,
            loginVerifyService: loginVerifyService
        });

        scope.wordForm = {$valid:true};

    }));

    beforeEach(function () {
        adminHomePage = '/admin/partials/home.html';
        scope.formInfo.original = "data";
    });

    describe("When initializing a word", function () {
        beforeEach(function(){
            spyOn($location, 'search').andReturn({id: 1});
        });

        it("then should have people and writers", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);


            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.writers.length).toBe(2);
            expect(scope.writers[0].id).toBe(8);
            expect(scope.writers[1].id).toBe(11);


            expect(scope.people.length).toBe(2);
            expect(scope.writers[0].id).toBe(8);
            expect(scope.writers[1].id).toBe(11);

        });


        it("it should display the linked reflections", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.reflectionsWithoutTheDefault[0].ticked).toBeFalsy();
            expect(scope.reflectionsWithoutTheDefault[0].id).toBe(4);
            expect(scope.reflectionsWithoutTheDefault[1].ticked).toBeTruthy();
            expect(scope.reflectionsWithoutTheDefault[1].id).toBe(5);
        });

        it("it should not include the default reflection in other reflections dropdown options",function(){
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.reflectionsWithoutTheDefault[0].id).toBe(4);
            expect(scope.reflectionsWithoutTheDefault[1].id).toBe(5);

        });
        it("it should display the linked synonyms", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.synonyms[0].ticked).toBeTruthy();
        });

        it("it should display the linked related words", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.relatedWords[0].ticked).toBeTruthy();
        });

        it("it shouldn't display currently editing word in related words and synonyms", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.relatedWords[0].ticked).toBeTruthy();
            expect(scope.relatedWords[1]).toBeUndefined();
            expect(scope.synonyms[1]).toBeUndefined();
        });

    });

    describe("When saving or updating a word,", function () {
        beforeEach(function(){
            spyOn($location, 'search').andReturn({id: 1});
        });

        it("then should redirect to admin-home if saved successfully", function () {
            $httpBackend.expectPOST('/api/words', scope.formInfo).respond(200);

            scope.saveData();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe(adminHomePage);
        });
        it("then shouldn't redirect to admin-home if not saved successfully", function () {
            $httpBackend.expectPOST('/api/words', scope.formInfo).respond(500);

            scope.saveData();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe('');
            expect(scope.formInfo.displayAjabShaharTeam).not.toBe(undefined);
        });
        it("should append /images/ for thumbnail url,if it is just filename",function(){
            scope.formInfo.thumbnailUrl = "thumbnail.jpg";
            $httpBackend.expectPOST('/api/words', scope.formInfo).respond(200);

            scope.saveData();
            $httpBackend.flush();

            expect(scope.formInfo.thumbnailUrl).toBe('/images/thumbnail.jpg');
        });
        it("should not append /images/ for thumbnail url,if it already have /images appended in filename",function(){
            scope.formInfo.thumbnailUrl = "/images/thumbnail.jpg";
            $httpBackend.expectPOST('/api/words', scope.formInfo).respond(200);

            scope.saveData();
            $httpBackend.flush();

            expect(scope.formInfo.thumbnailUrl).toBe('/images/thumbnail.jpg');
        });
        it("should not append /images/ for thumbnail url,if the image is from internet source",function(){
            scope.formInfo.thumbnailUrl = "http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg";
            $httpBackend.expectPOST('/api/words', scope.formInfo).respond(200);

            scope.saveData();
            $httpBackend.flush();

            expect(scope.formInfo.thumbnailUrl).toBe('http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg');
        });

    });
    describe("When fetching a given word via an ID,", function () {
        beforeEach(function(){
            spyOn($location, 'search').andReturn({id: 1});
        });

        it("then should have the word's details, if the word exist", function () {

            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond({"reflections": []});
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.formInfo.wordOriginal).toBe('अकथ कथा');
        });
        it("then should have singers as a comma separated entries for a song", function () {

            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond({"reflections": []});
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.songs[0].menuTitle).toBe('Bhajan - (Gavra Devi)');
            expect(scope.songs[2].menuTitle).toBe('Kichhu din mone mone - (Parvathy Baul)');
        });
        it("then shouldn't have singers as a comma separated entries for a song, if there are no singers", function () {

            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond({"reflections": []});
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.songs[1].menuTitle).toBe('Hiye Kaaya Mein');
        });
    });
    describe("when saving the word for the first time",function(){
        it("By default, it should have all the reflections for reflections dropdown options",function(){

            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/people/summary?show=all").respond(test_peopleSummary);
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs").respond(song_summary);

            scope.init();
            $httpBackend.flush();

            expect(scope.reflectionsWithoutTheDefault.length).toBe(3);
        });
    });
});

var test_word_summaries =
     [
        {
            "id": 3,
            "wordOriginal": "hey",
            "wordTranslation": "hjkhf",
            "wordTransliteration": "ehy",
            "hindiIntroExcerpt": "",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": true
        },
        {
            "id": 1,
            "wordOriginal": "word1",
            "wordTranslation": "jlksjgkl",
            "wordTransliteration": "word1 transliteration",
            "hindiIntroExcerpt": "intro excerpt english",
            "englishIntroExcerpt": "intro excerpt hindi",
            "writers": [
                {
                    "id": 1,
                    "name": "Parvathy Baul",
                    "hindiName": "",
                    "primaryOccupation": ""
                }
            ],
            "rootWord": true
        }
    ];


var test_word = {
    "id": 3,
    "wordOriginal": "अकथ कथा",
    "wordTranslation": "Untellable Tale",
    "wordTransliteration": "Akath Katha",
    "englishIntroExcerpt": "Akath means inexpressible story.",
    "hindiIntroExcerpt": null,
    "diacritic": null,
    "isRootWord": false,
    "showOnLandingPage": true,
    "meaning": null,
    "wordIntroductions": [],
    "reflections": [
        {
            "id": 1,
            "title": "Poet is God says Vipul",
            "speaker": {
                "id": 16,
                "name": "Vipul Rikhi",
                "hindiName": ""
            }
        },
        {
            "id": 5,
            "title": "reflection",
            "speaker": null
        }
    ],
    "defaultReflection": {
        "id": 1,
        "title": "Poet is God says Vipul",
        "speaker": {
            "id": 16,
            "name": "Vipul Rikhi",
            "hindiName": "",
            "primaryOccupation": ""
        }
    },
    "relatedWords": test_word_summaries,
    "songs": [],
    "synonyms": test_word_summaries,
    "writers": [],
    "people": []
};

var test_reflection_summaries = {
    "reflections": [
        {
            "id": 4,
            "title": "Unbelievable",
            "speaker": {
                "id": 14,
                "name": "Gavra Devi",
                "hindiName": ""
            }
        },
        {
            "id": 1,
            "title": "Poet is God says Vipul",
            "speaker": {
                "id": 16,
                "name": "Vipul Rikhi",
                "hindiName": ""
            }
        },
        {
            "id": 5,
            "title": "reflection",
            "speaker": {
                "id": 17,
                "name": "Fakru",
                "hindiName": ""
            }
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
var test_peopleSummary = [
    {
        "id": 8,
        "name": "Roshik",
        "hindiName": "",
        "primaryOccupation": null
    },
    {
        "id": 11,
        "name": "Kabir",
        "hindiName": "",
        "primaryOccupation": null
    }
];