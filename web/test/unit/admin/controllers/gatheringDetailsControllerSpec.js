'use strict';

describe("Gathering details controller specs", function () {
    var scope,
        $location,
        $httpBackend,
        fakeWindow,
        redirectedURL;

    beforeEach(module("gatheringsAdminApp"));

    beforeEach(inject(function (_$controller_, _$rootScope_, _$window_, _$location_, gatheringContentService, _$httpBackend_, $cookies, loginVerifyService) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        $location = _$location_;
        fakeWindow = {location: {href: ''}};
        $cookies.user = "admin";

        _$controller_('gatheringDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: $location,
            gatheringContentService: gatheringContentService,
            loginVerifyService: loginVerifyService
        });
    }));

    describe("When saving a gathering", function () {
        it("Then should redirect to admin-home if saved successfully", function () {
            scope.formInfo.hindi = "data";
            scope.formInfo.english = "data";
            $httpBackend.expectPOST('/api/gatherings', scope.formInfo).respond(200);

            scope.saveData();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });
        it("Then should have alert if english title empty", function () {
            scope.formInfo.hindi = "data";
            scope.saveData();

            expect(scope.alert).toBe('English Title is mandatory');
        });
        it("Then should redirect if english title exist", function () {
            scope.formInfo.english = "data";
            $httpBackend.expectPOST('/api/gatherings', scope.formInfo).respond(200);

            scope.saveData();
            $httpBackend.flush();

            expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
        });
    });

    describe("When the URL has a gatheringID", function () {
        it("Then should set pageName to edit", function () {
            var mockedId = 1;
            spyOn($location, 'search').andReturn({id: mockedId});
            $httpBackend.when("GET", "/api/gatherings/1").respond({
                'id': 1,
                'hindi': 'hindiText',
                'english': 'englishText'
            });

            scope.getGatheringData();
            $httpBackend.flush();

            expect(scope.pageName).toBe('Edit');
        });
        it("Then should have the filled form values", function () {
            var mockedId = 1;
            spyOn($location, 'search').andReturn({id: mockedId});
            $httpBackend.when("GET", "/api/gatherings/1").respond({
                'id': 1,
                'hindi': 'hindiText',
                'english': 'englishText'
            });

            scope.getGatheringData();
            $httpBackend.flush();

            expect(scope.formInfo.hindi).toBe('hindiText');
            expect(scope.formInfo.english).toBe('englishText');
        });
    });

    describe("When the URL does not have a gatheringID", function () {
        it("Then should set pageName to Enter", function () {
            scope.getGatheringData();

            expect(scope.pageName).toBe('Enter');
        });
        it("Then should not have filled form values", function () {
            scope.getGatheringData();

            expect(scope.formInfo.hindi).toBe(undefined);
            expect(scope.formInfo.english).toBe(undefined);
        });
    });
});
