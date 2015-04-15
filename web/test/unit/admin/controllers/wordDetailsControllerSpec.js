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

        spyOn($location, 'search').andReturn({id: 1});

        _$controller_('wordDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: $location,
            contentService: contentService,
            PAGES: PAGES,
            loginVerifyService: loginVerifyService
        });


    }));

    beforeEach(function () {
        adminHomePage = '/admin/partials/home.html';
        scope.formInfo.original = "data";
    });

    describe("When initializing a word", function () {

        it("then should have people and writers", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);


            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/people").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond({
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }]
            });

            scope.init();
            $httpBackend.flush();

            expect(scope.writers).toBe("somePerson");
            expect(scope.people).toBe("somePerson");
        });

        it("it should display the linked reflections", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/people").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond({
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }]
            });

            scope.init();
            $httpBackend.flush();

            expect(scope.reflections[0].ticked).toBeFalsy();
            expect(scope.reflections[1].ticked).toBeTruthy();
            expect(scope.reflections[2].ticked).toBeTruthy();
        });
        it("it should display the linked synonyms", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/people").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond({
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }]
            });

            scope.init();
            $httpBackend.flush();

            expect(scope.synonyms[0].ticked).toBeTruthy();
        });

        it("it should display the linked related words", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/people").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond({
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }]
            });

            scope.init();
            $httpBackend.flush();

            expect(scope.relatedWords[0].ticked).toBeTruthy();
        });

        it("it shouldn't display currently editing word in related words and synonyms", function () {
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/people").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond(test_reflection_summaries);
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond({
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }]
            });

            scope.init();
            $httpBackend.flush();

            expect(scope.relatedWords[0].ticked).toBeTruthy();
            expect(scope.relatedWords[1]).toBeUndefined();
            expect(scope.synonyms[1]).toBeUndefined();
        });

    });


    describe("When saving a word,", function () {
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
    });
    describe("When updating a word,", function () {
        it("then should redirect to admin-home if saved successfully", function () {
            $httpBackend.expectPOST('/api/words/edit', scope.formInfo).respond(200);

            scope.updateWord();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe(adminHomePage);
        });
        it("then shouldn't redirect to admin-home if not saved successfully", function () {
            $httpBackend.expectPOST('/api/words/edit', scope.formInfo).respond(500);

            scope.updateWord();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe('');
        });
    });
    describe("When fetching a given word via an ID,", function () {
        it("then should have the word's details, if the word exist", function () {

            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond({"reflections": []});
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/people").respond({"people": ""});
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond({
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }]
            });

            scope.init();
            $httpBackend.flush();

            expect(scope.formInfo.wordOriginal).toBe('अकथ कथा');
        });
        it("then should have singers as a comma separated entries for a song", function () {

            var mockedSongs = {
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [{"name": "singer1"}, {"name": "singer2"}],
                    "words": {"words": []}
                }, {
                    "englishTransliterationTitle": "some title2",
                    "singers": [{"name": "singer3"}, {"name": "singer4"}],
                    "words": {"words": []}
                }]
            };
            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond({"reflections": []});
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/people").respond({"people": ""});
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond(mockedSongs);

            scope.init();
            $httpBackend.flush();

            expect(scope.songs[0].menuTitle).toBe('some title - (singer1, singer2)');
            expect(scope.songs[1].menuTitle).toBe('some title2 - (singer3, singer4)');
        });
        it("then shouldn't have singers as a comma separated entries for a song, if there are no singers", function () {
            var mockedSongs = {
                "songs": [{
                    "englishTransliterationTitle": "some title",
                    "singers": [],
                    "words": {"words": []}
                }, {"englishTransliterationTitle": "some title2", "singers": [], "words": {"words": []}}]
            };

            $httpBackend.expectGET("/api/words/edit?id=1").respond(test_word);

            $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
            $httpBackend.when("GET", "/api/category/word").respond(null);
            $httpBackend.when("GET", "/api/reflections/summary").respond({"reflections": []});
            $httpBackend.when("GET", "/api/words/summary").respond(test_word_summaries);
            $httpBackend.when("GET", "/api/people").respond({"people": ""});
            $httpBackend.when("GET", "/api/songs/getAllSongs").respond(mockedSongs);

            scope.init();
            $httpBackend.flush();

            expect(scope.songs[0].menuTitle).toBe('some title');
            expect(scope.songs[1].menuTitle).toBe('some title2');
        });


    });
});

var test_word_summaries = {
    "words": [
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
    ]
};

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
    "relatedWords": test_word_summaries.words,
    "songs": [],
    "synonyms": test_word_summaries.words,
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