describe("word service",function(){

    var wordService;

    beforeEach(module("word"));

    beforeEach(inject(function (_wordService_) {
        wordService = _wordService_;
    }));

    describe("getReflectionsFrom",function(){

        it("should get reflections from the given word, without any duplicate or unpublished reflections",function(){
            var reflections = wordService.getReflectionsFrom(test_wordService_word);

            for(var i=0;i<reflections.length-1;i++){
                for(var j=i+1;j<reflections.length;j++){
                    expect(reflections[i].id === reflections[j].id).toBeFalsy();
                }
            }

            expect(reflections.some(function(reflection){
                return !reflection.published
            })).toBeFalsy();


            expect(reflections.length).toBe(3);
        });

        it("should get reflections and always place the default reflection at the start of the list",function(){
            var reflections = wordService.getReflectionsFrom(test_wordService_word);
            expect(reflections.length).toBe(3);
            expect(reflections[0].id).toBe(12);
        });
    });

});

var test_wordService_word = {
    "id": 1,
    "wordOriginal": "अकथ कथा",
    "wordTranslation": "Untellable Tale",
    "wordTransliteration": "Akath Katha",
    "hindiIntroExcerpt": null,
    "diacritic": null,
    "thumbnailUrl": "/images/TN-Word-Intro-Akhath_katha.png",
    "isRootWord": true,
    "showOnLandingPage": true,
    "reflections": [
        {
            "thumbnailURL": "http://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Kabir004.jpg/400px-Kabir004.jpg",
            "id": 14,
            "title": "kabir wiki",
            "speaker": {
                "publish": true,
                "id": 13,
                "name": "Bananath",
                "hindiName": "",
                "primaryOccupation": "Poet"
            },
            "published": true,
            "excerpt": "kahe kabir blah blah..",
            "duration": null,
            "verb": null,
            "contentType": "text"
        },
        {
            "thumbnailURL": "/images/TN-R-One-Who-Has-Bid-Adieu-Kapil-Tiwari.png",
            "id": 7,
            "title": "One who bids adieu to the past and future is 'shoonya'",
            "speaker": {
                "publish": true,
                "id": 43,
                "name": "Kapil Tiwari",
                "hindiName": "",
                "primaryOccupation": "Folklorist"
            },
            "published": true,
            "excerpt": null,
            "duration": "4:04",
            "verb": "says",
            "contentType": "video"
        },
        {
            "thumbnailURL": "/images/TN-R-Do-We-Have-What-It-Takes-Ashok-Vajpeyi.png",
            "id": 12,
            "title": "Do we have what it takes to listen to Kabir?",
            "speaker": {
                "publish": true,
                "id": 52,
                "name": "Ashok Vajpeyi",
                "hindiName": "",
                "primaryOccupation": "Poet"
            },
            "published": true,
            "excerpt": "In this loud, dazzling and crowded world, to be able to reach a point where you can even listen to that naked, unarmed, unadorned voice of Kabir…. is difficult. This is the problem of our times.",
            "duration": "2:28",
            "verb": "asks",
            "contentType": "video"
        },
        {
            "thumbnailURL": "/images/TN-R-Do-We-Have-What-It-Takes-to-pass.png",
            "id": 22,
            "title": "Do we have what it takes to listen to k sanu?",
            "speaker": {
                "publish": true,
                "id": 2,
                "name": "k sanu",
                "hindiName": "",
                "primaryOccupation": "singer"
            },
            "published": false,
            "excerpt": "In this loud, dazzling and crowded world",
            "duration": "2:28",
            "verb": "asks",
            "contentType": "video"
        }
    ],
    "displayAjabShaharTeam": true,
    "defaultReflection": {
        "thumbnailURL": "/images/TN-R-Do-We-Have-What-It-Takes-Ashok-Vajpeyi.png",
        "id": 12,
        "title": "Do we have what it takes to listen to Kabir?",
        "speaker": {
            "publish": true,
            "id": 52,
            "name": "Ashok Vajpeyi",
            "hindiName": "",
            "primaryOccupation": "Poet"
        },
        "published": true,
        "excerpt": "In this loud, dazzling and crowded world, to be able to reach a point where you can even listen to that naked, unarmed, unadorned voice of Kabir…. is difficult. This is the problem of our times.",
        "duration": "2:28",
        "verb": "asks",
        "contentType": "video"
    },
    "publish": true
};