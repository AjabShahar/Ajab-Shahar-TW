describe("Song Explore Helper",function(){
    it("should get reflections related to related words of the song", function () {
        var reflections = AjabShahar.SongExploreHelper.getReflectionsFromWordReflections(song_explore_helper_reflectionSummaries);

        expect(reflections.length).toBe(5);

        var reflectionIds = reflections.map(function(reflection){
            return reflection.id;
        });

        expect(_.contains([4,8,6,7,1,5],reflectionIds[0])).toBeTruthy();
        expect(_.contains([4,8,6,7,1,5],reflectionIds[1])).toBeTruthy();
        expect(_.contains([4,8,6,7,1,5],reflectionIds[2])).toBeTruthy();
        expect(_.contains([4,8,6,7,1,5],reflectionIds[3])).toBeTruthy();
        expect(_.contains([4,8,6,7,1,5],reflectionIds[4])).toBeTruthy();

    });

    it("should get songs related to related words of the song", function () {
        var songs = AjabShahar.SongExploreHelper.getSongsFromWordReflections(song_explore_helper_songSummaries);

        expect(songs.length).toBe(5);

        var songIds = songs.map(function(song){
            return song.id;
        });

        expect(_.contains([8,6,19,18,5,11],songIds[0])).toBeTruthy();
        expect(_.contains([8,6,19,18,5,11],songIds[1])).toBeTruthy();
        expect(_.contains([8,6,19,18,5,11],songIds[2])).toBeTruthy();
        expect(_.contains([8,6,19,18,5,11],songIds[3])).toBeTruthy();
        expect(_.contains([8,6,19,18,5,11],songIds[4])).toBeTruthy();

    });

    it("should display only the words that are published and are root words", function () {
        var wordThumbnails = AjabShahar.SongExploreHelper.createWordThumbnails(song_explore_helper_words);

        var wordIds = wordThumbnails.map(function(wordThumbnail){
            return wordThumbnail.id;
        });
        expect(_.contains(wordIds,5)).toBeFalsy();

    });

    it("should display only reflections that are published", function () {
        var reflectionThumbnails = AjabShahar.SongExploreHelper.createReflectionThumbnails(song_explore_helper_reflectionSummaries[0].reflections);

        var refIds = reflectionThumbnails.map(function(refThumbnail){
            return refThumbnail.id;
        });
        expect(_.contains(refIds,6)).toBeFalsy();
    });

    it("should show at most 3 reflections per word", function () {
        var reflections = AjabShahar.SongExploreHelper.getReflectionsFromWordReflections(song_explore_helper_reflectionSummaries);

        expect(reflections.length).toBe(5);

        var reflectionIds = reflections.map(function(reflection){
            return reflection.id;
        });

        expect(_.contains([4,8,6,7],reflectionIds[0])).toBeTruthy();
        expect(_.contains([4,8,6,7],reflectionIds[1])).toBeTruthy();
        expect(_.contains([4,8,6,7],reflectionIds[2])).toBeTruthy();
        expect(_.contains([1,5],reflectionIds[3])).toBeTruthy();
        expect(_.contains([1,5],reflectionIds[4])).toBeTruthy();
    });

    it("should show at most 3 songs per word", function () {
        var songs = AjabShahar.SongExploreHelper.getSongsFromWordReflections(song_explore_helper_songSummaries);

        expect(songs.length).toBe(5);

        var songIds = songs.map(function(song){
            return song.id;
        });

        expect(_.contains([8,6],songIds[0])).toBeTruthy();
        expect(_.contains([8,6],songIds[1])).toBeTruthy();
        expect(_.contains([19,18,5,11],songIds[2])).toBeTruthy();
        expect(_.contains([19,18,5,11],songIds[3])).toBeTruthy();
        expect(_.contains([19,18,5,11],songIds[4])).toBeTruthy();
    });
});

var song_explore_helper_reflectionSummaries =
    [
        {
            word: {
                "id": 6,
                "wordOriginal": "जागना सोना",
                "wordTranslation": "Waking Sleeping",
                "wordTransliteration": "Jaagna Sona",
                "hindiIntroExcerpt": "",
                "englishIntroExcerpt": "",
                "writers": [],
                "rootWord": true,
                "publish": true
            },
            "reflections": [
                {
                    "id": 7,
                    "title": "reflection",
                    "speaker": null,
                    "published": true,
                    "thumbnailImg": null,
                    "excerpt": null,
                    "duration": null,
                    "verb": "by",
                    "contentType": "video"
                },
                {
                    "id": 6,
                    "title": "reflection1",
                    "speaker": {
                        "id": 36,
                        "name": "hjhj jhjkahf",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    "published": false,
                    "thumbnailImg": null,
                    "excerpt": null,
                    "duration": null,
                    "verb": "reflec",
                    "contentType": ""
                },
                {
                    "id": 8,
                    "title": "Music Takes the Poem Out of the Mind",
                    "speaker": {
                        "id": 21,
                        "name": "Sumar Kadu Jat",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    "published": true,
                    "thumbnailImg": null,
                    "excerpt": "“When the words of a poem start occupying your stomach instead of your cerebral cortex (through music), it’s a complete transformation in your experience of the so-called poem!”",
                    "duration": "5:00",
                    "verb": "says",
                    "contentType": "text"
                },
                {
                    "id": 4,
                    "title": "Unbelievable",
                    "speaker": {
                        "id": 14,
                        "name": "Gavra Devi",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    "published": true,
                    "thumbnailImg": "http://files1.coloribus.com/files/adsarchive/part_807/8077505/file/halls-light-cough-sweets-unbelievable-small-56470.jpg",
                    "excerpt": "Angela Belcher looks to nature for inspiration on how to engineer viruses to create extraordinary new materials",
                    "duration": "10:00",
                    "verb": "says",
                    "contentType": "video"
                }
            ]
        },
        {
            word: {
                "id": 4,
                "wordOriginal": "शून्य",
                "wordTranslation": "Emptiness",
                "wordTransliteration": "Shoonya",
                "hindiIntroExcerpt": "Shoonya is literally zero",
                "englishIntroExcerpt": "",
                "writers": [],
                "rootWord": true,
                "publish": true
            },
            "reflections": [
                {
                    "id": 5,
                    "title": "reflection",
                    "speaker": {
                        "id": 17,
                        "name": "Fakru",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    "published": true,
                    "thumbnailImg": null,
                    "excerpt": null,
                    "duration": null,
                    "verb": "reflection",
                    "contentType": "text"
                },
                {
                    "id": 1,
                    "title": "Poet is God says Vipul",
                    "speaker": {
                        "id": 16,
                        "name": "Vipul Rikhi",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    "published": true,
                    "thumbnailImg": null,
                    "excerpt": null,
                    "duration": null,
                    "verb": "says",
                    "contentType": "video"
                },
                {
                    "id": 11,
                    "title": "Poet is a person who writes poems says pankaj",
                    "speaker": {
                        "id": 17,
                        "name": "pankaj advani",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    "published": false,
                    "thumbnailImg": null,
                    "excerpt": null,
                    "duration": null,
                    "verb": "says",
                    "contentType": "video"
                }
            ]
        }];

var song_explore_helper_songSummaries = [
    {
        "word": {
            "id": 6,
            "wordOriginal": "जागना सोना",
            "wordTranslation": "Waking Sleeping",
            "wordTransliteration": "Jaagna Sona",
            "hindiIntroExcerpt": "",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": true,
            "publish": true
        },
        "songs": [
            {
                "id": 6,
                "englishTranslationTitle": "The Cart of Meditation is Tottering",
                "englishTransliterationTitle": "Bhajan Ro Gudak Rahyo Gaado",
                "singers": [
                    {
                        "id": 14,
                        "name": "Gavra Devi",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "poet": [
                    {
                        "id": 17,
                        "name": "Fakru",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "duration": "05:10",
                "category": "Songs",
                "thumbnailUrl": "https://farm9.staticflickr.com/8588/15609516253_155aac2066_o.jpg",
                "publish": true
            },
            {
                "id": 8,
                "englishTranslationTitle": "In This Body",
                "englishTransliterationTitle": "Hiye Kaaya Mein",
                "singers": [
                    {
                        "id": 32,
                        "name": "Kaluram Bamaniya",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "poet": [
                    {
                        "id": 11,
                        "name": "Kabir",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "duration": "04.55",
                "category": "Songs",
                "thumbnailUrl": "https://farm9.staticflickr.com/8632/15663990083_cac15c4210_o.jpg",
                "publish": true
            },
            {
                "id": 118,
                "englishTranslationTitle": "1111 In This Body",
                "englishTransliterationTitle": "111 Hiye Kaaya Mein",
                "singers": [
                    {
                        "id": 32,
                        "name": "Kaluram Bamaniya",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "poet": [
                    {
                        "id": 11,
                        "name": "Kabir",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "duration": "04.55",
                "category": "Songs",
                "thumbnailUrl": "https://farm9.staticflickr.com/8632/15663990083_cac15c4210_o.jpg",
                "publish": false
            }
        ]
    },
    {
        word: {
            "id": 4,
            "wordOriginal": "शून्य",
            "wordTranslation": "Emptiness",
            "wordTransliteration": "Shoonya",
            "hindiIntroExcerpt": "Shoonya is literally zero",
            "englishIntroExcerpt": "",
            "writers": [],
            "rootWord": true,
            "publish": true
        },
        "songs": [
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
                "poet": [
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
                "publish": true
            },
            {
                "id": 5,
                "englishTranslationTitle": "In This Body",
                "englishTransliterationTitle": "Hiye Kaaya Mein",
                "singers": [
                    {
                        "id": 10,
                        "name": "Mooralala Marwada",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    {
                        "id": 21,
                        "name": "Sumar Kadu Jat",
                        "hindiName": "",
                        "primaryOccupation": null
                    },
                    {
                        "id": 27,
                        "name": "Parvathy Baul",
                        "hindiName": "",
                        "primaryOccupation": "Singer"
                    }
                ],
                "poet": [
                    {
                        "id": 24,
                        "name": "Shah Abdul Latif Bhitai",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "duration": "07:05",
                "category": "Songs",
                "thumbnailUrl": "https://farm8.staticflickr.com/7513/16257916986_baf4e108cb_o.jpg",
                "publish": true
            },
            {
                "id": 18,
                "englishTranslationTitle": "When the mind’s intoxicated",
                "englishTransliterationTitle": "Mann Mast Hua Phir Kya Bole?",
                "singers": [
                    {
                        "id": 15,
                        "name": "Shabnam Virmani",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "poet": [
                    {
                        "id": 13,
                        "name": "Kabir",
                        "hindiName": "कबीर",
                        "primaryOccupation": null
                    }
                ],
                "duration": "7:15",
                "category": "Songs",
                "thumbnailUrl": "http://gaatha.com/wp-content/uploads/2013/06/Kabir-das-ji.jpg",
                "publish": true
            },
            {
                "id": 19,
                "englishTranslationTitle": "When the mind’s intoxicated 22",
                "englishTransliterationTitle": "Mann Mast Hua Phir Kya Bole? 22",
                "singers": [
                    {
                        "id": 15,
                        "name": "Shabnam Virmani",
                        "hindiName": "",
                        "primaryOccupation": null
                    }
                ],
                "poet": [
                    {
                        "id": 13,
                        "name": "Kabir",
                        "hindiName": "कबीर",
                        "primaryOccupation": null
                    }
                ],
                "duration": "7:15",
                "category": "Songs",
                "thumbnailUrl": "http://gaatha.com/wp-content/uploads/2013/06/Kabir-das-ji.jpg",
                "publish": true
            }
        ]
    }
];

var song_explore_helper_words = [
    {
        "id": 6,
        "wordOriginal": "जागना सोना",
        "wordTranslation": "Waking Sleeping",
        "wordTransliteration": "Jaagna Sona",
        "hindiIntroExcerpt": "",
        "englishIntroExcerpt": "",
        "writers": [],
        "rootWord": true,
        "publish": true
    },
    {
        "id": 4,
        "wordOriginal": "शून्य",
        "wordTranslation": "Emptiness",
        "wordTransliteration": "Shoonya",
        "hindiIntroExcerpt": "Shoonya is literally zero in the Indian numbering system, the place from which all things begin. It is also a technical term in Buddhism, usually translated as emptiness or nothingness. In Sanskrit, one of its primary meanings is empty space, or void.",
        "englishIntroExcerpt": "",
        "writers": [],
        "rootWord": true,
        "publish": true
    },
    {
        "id": 5,
        "wordOriginal": "शून्य",
        "wordTranslation": "Emptiness",
        "wordTransliteration": "Shoonya 2",
        "hindiIntroExcerpt": "Shoonya 2  is literally zero ",
        "englishIntroExcerpt": "",
        "writers": [],
        "rootWord": true,
        "publish": false
    }


];