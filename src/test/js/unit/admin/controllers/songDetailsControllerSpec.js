'use strict'

describe("Song details controller specs", function() {
	var scope,
		$httpBackend,
		fakeWindow,
		$location,
		ADMIN_PAGES;

	beforeEach(module('songsAdminApp'));

	beforeEach(inject(function(_$controller_, _$rootScope_, _$window_, _$location_, songContentService, PAGES, _$filter_, _$httpBackend_) {
		scope = _$rootScope_.$new();
		$location = _$location_;
		ADMIN_PAGES = PAGES;
		$httpBackend = _$httpBackend_;
		fakeWindow = { location: { href: '' } };

		_$controller_('songDetailsController', {
			$scope: scope,
			$window: fakeWindow,
			$location: _$location_,
			songContentService: songContentService,
			PAGES: PAGES,
			$filter: _$filter_
		});
	}));

	var insertTestData = function(){
		scope.mediaCategories = [{"name":"audio & video"}, {"name":"audio only"}];
		scope.song = {'youtubeVideoId': '1e1', 'publishedDate': 'someDate'};
	};

	beforeEach(insertTestData);

	describe("When saving a song", function() {
		it("then should redirect to the edit of song admin", function() {
			$httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('/admin/home.html');
		});
		it("then should set audio & video as media category, if it has a youtube id", function() {
			$httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

			scope.saveData();
			$httpBackend.flush();

			expect(scope.song.mediaCategory.name).toBe('audio & video');
		});
		it("then should set audio only as media category, if it does not have a youtube id", function() {
			scope.song = {};
			$httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

			scope.saveData();
			$httpBackend.flush();

			expect(scope.song.mediaCategory.name).toBe('audio only');
		});
	});

	describe("When updating a song", function() {
		it("then should redirect to the admin homepage", function() {
			$httpBackend.expectPOST('/api/songs/edit', scope.song).respond(200);

			scope.updateSong();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('/admin/home.html');
			
			expect(scope.song.mediaCategory.name).toBe('audio & video');
		});
		it("then should have published date as null", function() {
			$httpBackend.expectPOST('/api/songs/edit', scope.song).respond(200);

			scope.updateSong();
			$httpBackend.flush();

			expect(scope.song.publishedDate).toBe(null);
		});
	});
});