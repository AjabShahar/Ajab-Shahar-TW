'use strict';

describe("Genre betails controller specs", function(){
	var scope,
		$location,
		$httpBackend,
		fakeWindow,
		redirectedURL;

	beforeEach(module("genresAdminApp"));

	beforeEach(inject(function(_$controller_, _$rootScope_, _$window_, _$location_, genreContentService, _$httpBackend_){
		scope = _$rootScope_.$new();
		$httpBackend = _$httpBackend_;
		$location = _$location_;
		fakeWindow = { location: { href: '' } };

		_$controller_('genreDetailsController', {
			$scope: scope,
			$window: fakeWindow,
			$location: $location,
			genreContentService: genreContentService
		});
	}));

	describe("When saving a  genre", function(){
		it("Then should redirect to admin-home if saved successfully", function(){
			scope.formInfo.original = "data";
			scope.formInfo.english = "data";

			$httpBackend.expectPOST('/api/genres', scope.formInfo).respond(200);

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('/admin/home.html');
		});
		it("Then should have alert if fields are empty", function(){
			scope.saveData();

			expect(scope.alert).toBe('Please fill in all the fields');
		});
		it("Then should not redirect if either of the fields are empty", function(){
			scope.formInfo.original = "data";
			scope.saveData();

			expect(fakeWindow.location.href).toBe('');
		});
	});

	describe("When the URL has a genreID", function(){
		it("Then should set pageName to edit", function(){
			var mockedId = 1;
			spyOn($location, 'search').andReturn({ id: mockedId });
			$httpBackend.when("GET", "/api/genres/1").respond({'id': 1, 'original': 'originalText', 'english': 'englishText'});

			scope.getGenreData();
			$httpBackend.flush();

			expect(scope.pageName).toBe('Edit');
		});
		it("Then should have the filled form values", function(){
			var mockedId = 1;
			spyOn($location, 'search').andReturn({ id: mockedId });
			$httpBackend.when("GET", "/api/genres/1").respond({'id': 1, 'original': 'originalText', 'english': 'englishText'});

			scope.getGenreData();
			$httpBackend.flush();

			expect(scope.formInfo.original).toBe('originalText');
			expect(scope.formInfo.english).toBe('englishText');
		});
	});

	describe("When the URL does not have a genreID", function(){
		it("Then should set pageName to Enter", function(){
			scope.getGenreData();

			expect(scope.pageName).toBe('Enter');
		});
		it("Then should not have filled form values", function(){
			scope.getGenreData();

			expect(scope.formInfo.original).toBe(undefined);
			expect(scope.formInfo.english).toBe(undefined);
		});
	});
});