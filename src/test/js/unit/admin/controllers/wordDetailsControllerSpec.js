'use strict'

describe("Word details controller spec:", function() {
	var scope,
		fakeWindow,
		$location,
		$httpBackend;

	beforeEach(module('wordsAdminApp'));

	beforeEach(inject(function(_$controller_, _$rootScope_, _$window_, _$location_, contentService, PAGES, _$httpBackend_){
		scope = _$rootScope_.$new();
		fakeWindow = { location: { href: '' } };
		$location = _$location_;
		$httpBackend = _$httpBackend_;

		_$controller_('wordDetailsController', {
			$scope: scope,
			$window: fakeWindow,
			$location: $location,
			contentService: contentService,
			PAGES: PAGES
		});
	}));

	describe("When saving a word,", function() {
		it("then should redirect to admin-home if saved successfully", function() {
			var adminHomePage = '/admin/home.html';
			scope.formInfo.original = "data";
			scope.formInfo.english = "data";
			$httpBackend.expectPOST('/api/words', scope.formInfo).respond(200);

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe(adminHomePage);
		});
		it("then shouldn't redirect to admin-home if not saved successfully", function() {
			scope.formInfo.original = "data";
			scope.formInfo.english = "data";
			$httpBackend.expectPOST('/api/words', scope.formInfo).respond(500);

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('');
		});
	});
});