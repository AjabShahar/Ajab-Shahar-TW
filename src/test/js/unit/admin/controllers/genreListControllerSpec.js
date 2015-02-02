'use strict';

describe("Genre list controller specs", function() {
	var scope,
		$httpBackend;

	beforeEach(module("genresAdminApp"));

	beforeEach(inject(function(_$controller_, _$rootScope_, genreContentService, _$httpBackend_){
		scope = _$rootScope_.$new();
		$httpBackend = _$httpBackend_

		_$controller_('genreListController', {
			$scope: scope,
			genreContentService: genreContentService
		});
	}));

	describe("When app is intialized", function() {
		it("Then should have genres if there are genres", function() {
			$httpBackend.when("GET", "/api/genres").respond([ { 'id': 1, 'original': 'originalText', 'english': 'englishText' }, {}, {} ]);

			scope.init();
			$httpBackend.flush();

			expect(scope.genres.length).toBe(3);
			expect(scope.genres[0].original).toBe('originalText');
		});
		it("Then shouldn't have genres if there are not genres", function() {
			$httpBackend.when("GET", "/api/genres").respond([]);

			scope.init();
			$httpBackend.flush();

			expect(scope.genres.length).toBe(0);
		});
	});
});