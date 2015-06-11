describe("all people controller", function () {
    var httpBackend, scope;

    beforeEach(module("people"));

    beforeEach(inject(function (_$rootScope_, _$httpBackend_, peopleService, _$controller_) {
        scope = _$rootScope_.$new();
        httpBackend = _$httpBackend_;

        _$controller_('allPeopleController', {
            $scope: scope,
            peopleService: peopleService
        });
    }));

    it("should get all people", function () {
        httpBackend.when('GET', '/api/people').respond(response);
        httpBackend.flush();

        expect(scope.numberOfPeople).toBe(2);
    });

    it("should construct details object for people",function(){
        httpBackend.when('GET', '/api/people').respond(response);
        httpBackend.flush();

        expect(scope.people[0].name).toBe("Roshik");
        expect(scope.people[0].occupations.length).toBe(1);
        expect(scope.people[0].thumbnailImg).toBe(null);
        expect(scope.people[0].profile).toBe("profile");
    });

    it("should apply alphabet filter",function(){
        httpBackend.when('GET', '/api/people').respond(response);
        httpBackend.flush();

        scope.applyAlphabetFilter("R");

        expect(scope.numberOfPeople).toBe(1);
        expect(scope.people[0].name).toBe("Roshik");
        expect(scope.people[0].occupations.length).toBe(1);
        expect(scope.people[0].thumbnailImg).toBe(null);
        expect(scope.people[0].profile).toBe("profile");
    });

    it("should clear alphabet filter",function(){
        httpBackend.when('GET', '/api/people').respond(response);
        httpBackend.flush();

        scope.applyAlphabetFilter("ALL");

        expect(scope.numberOfPeople).toBe(2);
        expect(scope.people[0].name).toBe("Roshik");
        expect(scope.people[1].name).toBe("Parvathy Baul");
    });

    var response = {
        people: [
            {
                "id": 2,
                "firstName": "Roshik",
                "middleName": "",
                "lastName": "",
                "firstNameInHindi": null,
                "middleNameInHindi": null,
                "lastNameInHindi": null,
                "roles": [
                    "Poet"
                ],
                "primaryOccupation": null,
                "thumbnailURL": null,
                "profile": "profile"
            },
            {
                "id": 1,
                "firstName": "Parvathy",
                "middleName": "",
                "lastName": "Baul",
                "firstNameInHindi": null,
                "middleNameInHindi": null,
                "lastNameInHindi": null,
                "roles": [
                    "Poet"
                ],
                "primaryOccupation": null,
                "thumbnailURL": null,
                "profile": null
            }
        ]
    }
});