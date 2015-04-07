'use strict';

describe("Gathering list controller specs", function () {
    var scope,
        $httpBackend;

    beforeEach(module("gatheringsAdminApp"));

    beforeEach(inject(function (_$controller_, _$rootScope_, gatheringContentService, _$httpBackend_, $cookies, loginVerifyService) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        $cookies.user = "admin";

        _$controller_('gatheringListController', {
            $scope: scope,
            gatheringContentService: gatheringContentService,
            loginVerifyService: loginVerifyService
        });
    }));

    describe("When app is intialized", function () {
        it("Then should have gatherings if there are gatherings", function () {
            $httpBackend.when("GET", "/api/gatherings").respond([{
                'id': 1,
                'original': 'originalText',
                'english': 'englishText'
            }, {}, {}]);

            scope.init();
            $httpBackend.flush();

            expect(scope.gatherings.length).toBe(3);
            expect(scope.gatherings[0].original).toBe('originalText');
        });
        it("Then shouldn't have gatherings if there are not gatherings", function () {
            $httpBackend.when("GET", "/api/gatherings").respond([]);

            scope.init();
            $httpBackend.flush();

            expect(scope.gatherings.length).toBe(0);
        });
    });
});
