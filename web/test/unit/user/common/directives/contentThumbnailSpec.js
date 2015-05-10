describe("Thumbnail model", function () {
    it("should construct song thumbnail model from song representation", function () {
        var songThumbnail = new AjabShahar.ThumbnailObject(test_songRepresentation[0],"song");

        expect(songThumbnail.type).toBe("song");
        expect(songThumbnail.thumbnailImg).toBe("https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png");
        expect(songThumbnail.verbPeople.verb).toBe("SINGS");
        expect(songThumbnail.verbPeople.people).toBe("Mukhtiyar Ali");

        expect(songThumbnail.englishTitle).toBe("Everyday I Ask For Your Well Being");
        expect(songThumbnail.translitTitle).toBe("Nit Khair Manga");

        expect(songThumbnail.contentFormat).toBe("video");
        expect(songThumbnail.duration).toBe("07:45");

        expect(songThumbnail.secondaryVerbPeople.verb).toBe("POET");
        expect(songThumbnail.secondaryVerbPeople.people).toBe("Badar Muneer");

    });


    it("should construct song thumbnail model from song representation with proper singers information and content format", function () {
        var songThumbnail = new AjabShahar.ThumbnailObject(test_songRepresentation[1],"song");

        expect(songThumbnail.type).toBe("song");
        expect(songThumbnail.thumbnailImg).toBe("https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png");
        expect(songThumbnail.verbPeople.verb).toBe("SING");
        expect(songThumbnail.verbPeople.people).toBe("Jaan2 Mohammed & Mitha Khan Jat");

        expect(songThumbnail.englishTitle).toBe("In This Body");
        expect(songThumbnail.translitTitle).toBe("Hiye Kaaya Mein");

        expect(songThumbnail.contentFormat).toBe("audio");
        expect(songThumbnail.duration).toBe("05:45");

        expect(songThumbnail.secondaryVerbPeople.verb).toBe("POET");
        expect(songThumbnail.secondaryVerbPeople.people).toBe("Kabir");

    });

});

describe("content thumbnail directive",function(){

    it("should show singers information only when there is only one singer for the song ",function(){
        var songThumbnail = new AjabShahar.ThumbnailObject(test_songRepresentation[1],"song");

        expect(songThumbnail.showPrimaryVerbPeopleAlways()).toBeFalsy();
        expect(songThumbnail.showPrimaryVerbPeopleInDetails()).toBeTruthy();

        var songThumbnail = new AjabShahar.ThumbnailObject(test_songRepresentation[0],"song");

        expect(songThumbnail.showPrimaryVerbPeopleAlways()).toBeTruthy();
        expect(songThumbnail.showPrimaryVerbPeopleInDetails()).toBeFalsy();
    })
});
var test_songRepresentation = [
    {
        "id": 11,
        "englishTranslationTitle": "Everyday I Ask For Your Well Being",
        "englishTransliterationTitle": "Nit Khair Manga",
        "singers": [
            {
                "id": 25,
                "name": "Mukhtiyar Ali",
                "hindiName": "",
                "primaryOccupation": null
            }
        ],
        "poets": [
            {
                "id": 26,
                "name": "Badar Muneer",
                "hindiName": "",
                "primaryOccupation": null
            }
        ],
        "duration": "07:45",
        "category": "Songs",
        "thumbnailUrl": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png",
        "published": true,
        "contentFormat": "video"
    },
    {
        "id": 17,
        "englishTranslationTitle": "In This Body",
        "englishTransliterationTitle": "Hiye Kaaya Mein",
        "singers": [{
            "id": 22,
            "name": "Jaan2 Mohammed",
            "hindiName": "",
            "primaryOccupation": "Singer"
        },
            {
                "id": 20,
                "name": "Mitha Khan Jat",
                "hindiName": "",
                "primaryOccupation": null
            }],
        "poets": [{
            "id": 11,
            "name": "Kabir",
            "hindiName": "",
            "primaryOccupation": "Poet"
        }],
        "duration": "05:45",
        "category": "Songs",
        "thumbnailUrl": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png",
        "published": false,
        "contentFormat": "audio"
    }
];