describe("song list controller", function () {

    var scope, httpBackend;

    beforeEach(module('adminApp'));

    beforeEach(inject(function (_$rootScope_, _$controller_, contentService, loginVerifyService, $cookies, _$httpBackend_) {
        scope = _$rootScope_.$new();
        httpBackend = _$httpBackend_;
        $cookies.user = "admin";

        _$controller_('songListController', {
            $scope: scope,
            contentService: contentService,
            loginVerifyService: loginVerifyService
        });
    }));

    it("should construct proper objects from song summary", function () {
        httpBackend.when('GET', '/api/songs').respond(song_summaries);

        httpBackend.flush();
        expect(scope.songs[0].title).toBe("Kichhu din mone mone");
        expect(scope.songs[0].translatedTitle).toBe("For a few days,O Heart");
        expect(scope.songs[0].categoryName).toBe("Songs");
        expect(scope.songs[0].publish).toBe("Yes");
        expect(scope.songs[0].singerNames).toBe("Parvathy Baul");
        expect(scope.songs[0].poetNames).toBe("Roshik");
        expect(scope.songs[0].id).toBe(1);

        expect(scope.songs.length).toBe(2);
    });

});
var song_summaries = {
    "songs": [
        {
            "id": 1,
            "englishTranslationTitle": "For a few days,O Heart",
            "englishTransliterationTitle": "Kichhu din mone mone",
            "singers": [
                {
                    "id": 1,
                    "name": "Parvathy Baul",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "poet": [
                {
                    "id": 2,
                    "name": "Roshik",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "duration": "5:45",
            "category": "Songs",
            "thumbnailUrl": "http://3.bp.blogspot.com/-kwpgiMcXc24/TcOcowo6mTI/AAAAAAAAA9w/uNt6ZsJadDg/s1600/parvathy_baul03.jpg",
            "publish": true
        },
        {
            "id": 2,
            "englishTranslationTitle": "The Cart of Meditation is Tottering",
            "englishTransliterationTitle": "Bhajan Ro Guá¸ak Rahyo Gaaá¸o",
            "singers": [
                {
                    "id": 3,
                    "name": "Gavra Devi",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "poet": [
                {
                    "id": 4,
                    "name": "Fakru",
                    "hindiName": "",
                    "primaryOccupation": null
                }
            ],
            "duration": "5:10",
            "category": "Songs",
            "thumbnailUrl": "http://i.ytimg.com/vi/J4IU5tDlD_s/mqdefault.jpg",
            "publish": true
        }
    ]
};