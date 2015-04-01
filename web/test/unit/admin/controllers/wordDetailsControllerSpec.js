'use strict'

describe("Word details controller spec:", function() {
	var scope,
		fakeWindow,
		$location,
		$httpBackend,
		adminHomePage;

	beforeEach(module('wordsAdminApp'));

	beforeEach(inject(function(_$controller_, _$rootScope_, _$window_, _$location_, contentService, PAGES, _$httpBackend_,loginVerifyService,$cookies){
		scope = _$rootScope_.$new();
		fakeWindow = { location: { href: '' } };
		$location = _$location_;
		$httpBackend = _$httpBackend_;
		$cookies.user = "admin";

		_$controller_('wordDetailsController', {
			$scope: scope,
			$window: fakeWindow,
			$location: $location,
			contentService: contentService,
			PAGES: PAGES,
			loginVerifyService:loginVerifyService
		});
	}));

	beforeEach(function() {
		adminHomePage = '/admin/partials/home.html';
		scope.formInfo.original = "data";
	});

    describe("When initializing a word", function(){
       it("then should have people and writers", function(){
		   $httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
           $httpBackend.when("GET", "/api/people").respond({"people": "somePerson"});
           $httpBackend.when("GET", "/api/category/word").respond(null);
           $httpBackend.when("GET", "/api/reflections/all").respond(null);
           $httpBackend.when("GET", "/api/songs/getAllSongs").respond({"songs" : [{"englishTransliterationTitle": "some title", "singers": [] ,"words":{"words":[]}}]});

           scope.init();
           $httpBackend.flush();

           expect(scope.writers).toBe("somePerson");
           expect(scope.people).toBe("somePerson");
       });
    });

	describe("When saving a word,", function() {
		it("then should redirect to admin-home if saved successfully", function() {
			$httpBackend.expectPOST('/api/words', scope.formInfo).respond(200);

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe(adminHomePage);
		});
		it("then shouldn't redirect to admin-home if not saved successfully", function() {
			$httpBackend.expectPOST('/api/words', scope.formInfo).respond(500);

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('');
		});
	});
	describe("When updating a word,", function() {
		it("then should redirect to admin-home if saved successfully", function() {
			$httpBackend.expectPOST('/api/words/edit', scope.formInfo).respond(200);

			scope.updateWord();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe(adminHomePage);			
		});
		it("then shouldn't redirect to admin-home if not saved successfully", function() {
			$httpBackend.expectPOST('/api/words/edit', scope.formInfo).respond(500);

			scope.updateWord();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('');
		});
	});
	describe("When fetching a given word via an ID,", function() {
		it("then should have the word's details, if the word exist", function() {
			$httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
			$httpBackend.when("GET", "/api/category/word").respond(null);
			$httpBackend.when("GET", "/api/reflections/all").respond(null);
			$httpBackend.when("GET", "/api/people").respond({"people": ""});
			$httpBackend.when("GET", "/api/songs/getAllSongs").respond({"songs" : [{"englishTransliterationTitle": "some title", "singers": [] ,"words":{"words":[]}}]});
			var mockedId = 1;
			spyOn($location, 'search').andReturn({ id: mockedId });
			$httpBackend.when("GET", "/api/words/edit?id=1").respond({'id': 1, 'original': 'dummyOriginalText', 'english': 'dummyEnglishText'});

			scope.init();
			$httpBackend.flush();

			expect(scope.formInfo.original).toBe('dummyOriginalText');
		});
		it("then should have singers as a comma separated entries for a song", function() {
			var mockedSongs =  {"songs" : [ {"englishTransliterationTitle": "some title", "singers": [{"name": "singer1"}, {"name": "singer2"} ],"words":{"words":[]} } ,{"englishTransliterationTitle": "some title2", "singers": [{"name": "singer3"}, {"name": "singer4"} ],"words":{"words":[]}} ] } ;
			$httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
			$httpBackend.when("GET", "/api/category/word").respond(null);
			$httpBackend.when("GET", "/api/reflections/all").respond(null);
			$httpBackend.when("GET", "/api/people").respond({"people": ""});
			$httpBackend.when("GET", "/api/songs/getAllSongs").respond(mockedSongs);

			scope.init();
			$httpBackend.flush();

			expect(scope.songs[0].menuTitle).toBe('some title - (singer1, singer2)');
			expect(scope.songs[1].menuTitle).toBe('some title2 - (singer3, singer4)');
		});
		it("then shouldn't have singers as a comma separated entries for a song, if there are no singers", function() {
			var mockedSongs =  {"songs" : [ {"englishTransliterationTitle": "some title", "singers": [] ,"words":{"words":[]}} ,{"englishTransliterationTitle": "some title2", "singers": [],"words":{"words":[]}} ] } ;
			$httpBackend.when("GET", "/api/people?role=Poet").respond({"people": "somePerson"});
			$httpBackend.when("GET", "/api/category/word").respond(null);
			$httpBackend.when("GET", "/api/reflections/all").respond(null);
			$httpBackend.when("GET", "/api/people").respond({"people": ""});
			$httpBackend.when("GET", "/api/songs/getAllSongs").respond(mockedSongs);

			scope.init();
			$httpBackend.flush();

			expect(scope.songs[0].menuTitle).toBe('some title');
			expect(scope.songs[1].menuTitle).toBe('some title2');
		});
	});
});
