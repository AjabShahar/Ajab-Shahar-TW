describe("word list controller", function () {

    var scope, $httpBackend;
    beforeEach(module("adminApp"));

    beforeEach(inject(function (_$controller_, _$rootScope_, _$httpBackend_, contentService, loginVerifyService,$cookies) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;
        $cookies.user = "admin";

        _$controller_('wordListController', {
            $scope: scope,
            contentService: contentService,
            loginVerifyService: loginVerifyService
        });

    }));

    it("should get all words",function(){
        $httpBackend.when('GET','/api/words/summary').respond(words);
        $httpBackend.flush();

        expect(scope.words.length).toBe(2);

    });
    it("should have yes or no for published and unpublished reflections respectively",function(){
        $httpBackend.when('GET','/api/words/summary').respond(words);
        $httpBackend.flush();

        expect(scope.words[0].publish).toBe("No");
        expect(scope.words[1].publish).toBe("Yes");
        expect(scope.words[0].id).toBe(6);
        expect(scope.words[1].id).toBe(1);
        expect(scope.words[0].wordOrPhrase).toBe("word transliteration");
        expect(scope.words[0].meaning).toBe("translation");
    });

    var words = [
        {
            "id": 6,
            "wordOriginal": "second word",
            "wordTranslation": "translation",
            "wordTransliteration": "word transliteration",
            "hindiIntroExcerpt": "",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": false,
            "publish": false
        },
        {
            "id": 1,
            "wordOriginal": "word original",
            "wordTranslation": "translation of first word",
            "wordTransliteration": "first word",
            "hindiIntroExcerpt": "intro excerpt english",
            "englishIntroExcerpt": "",
            "writers": [
                {
                    "id": 1,
                    "name": "Parvathy Baul",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "rootWord": true,
            "publish": true
        }
    ]

});