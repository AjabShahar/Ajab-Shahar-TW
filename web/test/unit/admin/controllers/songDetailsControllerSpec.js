'use strict';

describe("Song details controller specs", function () {
    var scope,
        $httpBackend,
        fakeWindow,
        $location;

    beforeEach(module('songsAdminApp'));

    beforeEach(inject(function (_$controller_, _$rootScope_, _$window_, _$location_, songContentService, PAGES, _$filter_, _$httpBackend_, loginVerifyService, $cookies) {
        scope = _$rootScope_.$new();
        $location = _$location_;
        $httpBackend = _$httpBackend_;
        fakeWindow = {location: {href: ''}};
        $cookies.user = "admin";

        _$controller_('songDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: _$location_,
            songContentService: songContentService,
            PAGES: PAGES,
            $filter: _$filter_,
            loginVerifyService: loginVerifyService
        });
    }));

    var insertTestData = function () {
        scope.mediaCategories = [{"name": "audio & video"}, {"name": "audio only"}];
        scope.song = {'youtubeVideoId': '1e1', 'publishedDate': 'someDate'};
    };

    beforeEach(insertTestData);

    describe("When saving a song", function () {
        it("then should redirect to the edit of song admin", function () {
            $httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

            scope.saveData();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });
        it("then should set audio & video as media category, if it has a youtube id", function () {
            $httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

            scope.saveData();
            $httpBackend.flush();

            expect(scope.song.mediaCategory.name).toBe('audio & video');
        });
        it("then should set audio only as media category, if it does not have a youtube id", function () {
            scope.song = {};
            $httpBackend.expectPOST('/api/songs', scope.song).respond(200, '1');

            scope.saveData();
            $httpBackend.flush();

            expect(scope.song.mediaCategory.name).toBe('audio only');
        });
    });

    describe("When initializing a song,", function () {
        it("then should have list of gatherings", function () {
            $httpBackend.when("GET", "/api/genres").respond(null);
            $httpBackend.when("GET", "/api/title/song").respond(null);
            $httpBackend.when("GET", "/api/category/song").respond([]);
            $httpBackend.when("GET", "/api/category/media").respond(null);
            $httpBackend.when("GET", "/api/title/umbrella").respond(null);
            $httpBackend.when("GET", "/api/people/summary?show=all&role=Singer").respond({"people": ""});
            $httpBackend.when("GET", "/api/people/summary?show=all&role=Poet").respond({"people": ""});
            $httpBackend.when("GET", "/api/words").respond({"words": ""});
            $httpBackend.when("GET", "/api/gatherings").respond(["someGathering"]);
            $httpBackend.when("GET", "/api/reflections").respond(["someReflections"]);
            $httpBackend.when("GET", "/api/songs/undefined").respond({"songGenre": ""});

            scope.init();
            $httpBackend.flush();

            expect(scope.gatherings[0]).toBe("someGathering");
        });
    });

    describe("When updating a song", function () {
        it("then should redirect to the admin homepage", function () {
            $httpBackend.expectPOST('/api/songs', scope.song).respond(200);

            scope.updateSong();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');

            expect(scope.song.mediaCategory.name).toBe('audio & video');
        });
        it("then should have published date as null", function () {
            $httpBackend.expectPOST('/api/songs', scope.song).respond(200);

            scope.updateSong();
            $httpBackend.flush();

            expect(scope.song.publishedDate).toBe(null);
        });
    });

    describe("When getting a song", function () {
        it("then should have the selected contents", function () {
            var mockedId = 1;
            spyOn($location, 'search').andReturn({id: mockedId});
            scope.words = [{"id": 2}, {"id": 1}, {"id": 3}, {"id": 4}];
            $httpBackend.when("GET", "/api/songs/1").respond({
                "words": [{"id": 1}, {"id": 2}],
                "songText": {"songTextContents": "blah"}
            });

            scope.getSongData();
            $httpBackend.flush();

            expect(scope.words[0].ticked).toBe(true);
            expect(scope.words[1].ticked).toBe(true);
            expect(scope.words[2].ticked).toBe(false);
            expect(scope.words[3].ticked).toBe(false);
        });
        it("should not throw exception if empty song text contents", function () {
            var mockedId = 1;
            spyOn($location, 'search').andReturn({id: mockedId});
            scope.words = [{"id": 2}, {"id": 1}, {"id": 3}, {"id": 4}];
            $httpBackend.when("GET", "/api/songs/1").respond({"words": [{"id": 1}, {"id": 2}]});

            scope.getSongData();
            $httpBackend.flush();

            expect(scope.getSongData).not.toThrow();

        });
    });
});
