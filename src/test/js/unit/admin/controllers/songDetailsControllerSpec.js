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

	describe("When saving a song", function() {
		it("then should redirect to the edit of song admin", function() {
			scope.song = {'youtubeVideoId': '1e1'};
			$httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

			scope.saveData();
			$httpBackend.flush();

			expect(fakeWindow.location.href).toBe('/admin/songs/edit.html?id=1');
		});
	});
});