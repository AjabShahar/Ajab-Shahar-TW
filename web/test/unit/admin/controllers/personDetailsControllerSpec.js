describe("person details controller", function () {

    var scope, httpBackend,$location,fakeWindow;
    beforeEach(module("personAdminApp"));

    beforeEach(inject(function (_$rootScope_, _$controller_, _$httpBackend_, _$location_, _$window_, _contentService_, _loginVerifyService_, $cookies) {
        scope = _$rootScope_.$new();
        $location = _$location_;
        httpBackend = _$httpBackend_;
        fakeWindow = {location: {href: ''}};
        $cookies.user = "admin";

        _$controller_('personDetailsController', {
            $scope: scope,
            $window: fakeWindow,
            $location: $location,
            contentService: _contentService_,
            loginVerifyService: _loginVerifyService_
        });

        httpBackend.when('GET', '/api/category/person').respond(person_categories);


    }));

    it("should have categories related to the person",function(){
        scope.init();
        httpBackend.flush();

        expect(scope.categoryList.length).toBe(2);
        expect(scope.primaryOccupationsList.length).toBe(2);
    });

    it("should save person details", function () {
        scope.init();
        httpBackend.flush();

        httpBackend.when('POST', '/api/people').respond(200);

        scope.saveData();
        httpBackend.flush();

        expect(fakeWindow.location.href).toBe('/admin/partials/home.html');
    });
    it("should append /images/ for thumbnail url,if it is just filename", function () {
        scope.init();
        httpBackend.flush();

        scope.formInfo.thumbnailURL = "thumbnail.jpg";
        httpBackend.when('POST', '/api/people').respond(200);

        scope.saveData();
        httpBackend.flush();

        expect(scope.formInfo.thumbnailURL).toBe('/images/thumbnail.jpg');
    });
    it("should not append /images/ for thumbnail url,if it already have /images appended in filename", function () {
        scope.init();
        httpBackend.flush();

        scope.formInfo.thumbnailURL = "/images/thumbnail.jpg";
        httpBackend.when('POST', '/api/people').respond(200);

        scope.saveData();
        httpBackend.flush();

        expect(scope.formInfo.thumbnailURL).toBe('/images/thumbnail.jpg');
    });
    it("should not append /images/ for thumbnail url,if the image is from internet source", function () {
        scope.init();
        httpBackend.flush();

        scope.formInfo.thumbnailURL = "http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg";
        httpBackend.when('POST', '/api/people').respond(200);

        scope.saveData();
        httpBackend.flush();

        expect(scope.formInfo.thumbnailURL).toBe('http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg');
    });

    describe("while editing person details",function(){

        it("should get the person details based on id", function () {
            spyOn($location, 'search').andReturn({id: 1});
            httpBackend.when('GET', '/api/people/1').respond(person);
            scope.init();
            httpBackend.flush();

            expect(scope.formInfo.id).toBe(1);
            expect(scope.formInfo.firstName).toBe("Parvathy");

        });
        it("should get the mark the selected occupations to true", function () {
            spyOn($location, 'search').andReturn({id: 1});
            httpBackend.when('GET', '/api/people/1').respond(person);
            scope.init();
            httpBackend.flush();

            expect(scope.categoryList[0].ticked).toBeTruthy();

        });
        it("should have the primary occupation selected if it is selected while saving",function(){
            scope.formInfo.primaryOccupation = scope.categoryList[0];
            httpBackend.when('POST', '/api/people').respond(200);

            scope.init();
            scope.saveData();

            spyOn($location, 'search').andReturn({id: 1});
            httpBackend.when('GET', '/api/people/1').respond(person);
            scope.init();
            httpBackend.flush();

            expect(scope.formInfo.primaryOccupation.name).toBe("Singer");

        });
        it("shouldn't have the selected primary occupation in other occupations list",function(){
            spyOn($location, 'search').andReturn({id: 1});
            httpBackend.when('GET', '/api/people/1').respond(person);
            scope.init();
            httpBackend.flush();

            expect(scope.categoryList[1]).toBeUndefined();

        });
    });


    var person = {
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
        "primaryOccupation": {
            "id": 10,
            "name": "Singer",
            "categoryType": "person"
        },
        "thumbnailURL": null,
        "profile": null
    };

    var person_categories = [
        {
            "id": 9,
            "name": "Poet",
            "categoryType": "person"
        },
        {
            "id": 10,
            "name": "Singer",
            "categoryType": "person"
        }
    ];
});