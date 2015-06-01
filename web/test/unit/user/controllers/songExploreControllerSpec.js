"use strict";

describe("Song Explore controller", function () {

    var httpBackend, scope, $location;
    beforeEach(module("song"));

    beforeEach(inject(function (_$httpBackend_, _$route_, _$rootScope_, _$controller_, _songsContentService_) {
        httpBackend = _$httpBackend_;
        scope = _$rootScope_.$new();

        _$controller_("songExploreController", {
            $scope: scope,
            $route: {
                current: {params: {songId: 18}}
            },
            songsContentService: _songsContentService_
        });
        httpBackend.when('GET', '/api/songs/getPublishedSongs/18').respond(song_explore_testSong);
        httpBackend.when('GET', '/api/words/?ids=6&ids=4&representation=custom').respond(song_explore_wordCustomSummaries);
    }));


    it("should add words directly related to songs to the list of thumbnails to be displayed", function () {
        httpBackend.flush();

        var wordThumbnailCount = 0;
        scope.thumbnails.forEach(function (thumbnail) {
            thumbnail.type === 'word' ? wordThumbnailCount++ : "";
        });

        expect(wordThumbnailCount).toBe(2);

    });

    it("should add reflections directly related to the song and reflections related to related words to the list of thumbnails to be displayed", function () {
        httpBackend.flush();

        var reflectionThumbnailCount = 0;
        scope.thumbnails.forEach(function (thumbnail) {
            thumbnail.type === 'reflection' ? reflectionThumbnailCount++ : "";
        });

        expect(reflectionThumbnailCount).toBe(5);
    });

    it("should add songs related to related words of the song to the list of thumbnails to be displayed", function () {
        httpBackend.flush();

        var songThumbnailCount = 0;
        scope.thumbnails.forEach(function (thumbnail) {
            thumbnail.type === 'song' ? songThumbnailCount++ : "";
        });

        expect(songThumbnailCount).toBe(5);
    });

    it("should not show duplicate thumbnails for repeated content",function(){
        httpBackend.flush();

        for(var i=0 ; i<scope.thumbnails.length;i++){
            for(var j=i+1;j<scope.thumbnails.length;j++){
                var  thumbnailI = scope.thumbnails[i].type +"_"+scope.thumbnails[i].id;
                var  thumbnailJ = scope.thumbnails[j].type +"_"+scope.thumbnails[j].id;
                expect(thumbnailI === thumbnailJ).toBeFalsy(thumbnailI+" : I = "+i+" j="+j);
            }
        }
    });


    it("should not show a thumbnail of the song being explored",function(){
        httpBackend.flush();

        var thumbnails = scope.thumbnails.map(function(thumbnail){
            return thumbnail.type+"_"+thumbnail.id;
        });
        expect(_.contains(thumbnails,"song_18")).toBeFalsy();
        expect(_.contains(thumbnails,"song_19")).toBeTruthy();
    });

});

var song_explore_testSong = {
    "id": 18,
    "isAuthoringComplete": true,
    "showOnLandingPage": true,
    "youtubeVideoId": "dxQmTudsYa0",
    "soundCloudTrackId": null,
    "thumbnailURL": "http://gaatha.com/wp-content/uploads/2013/06/Kabir-das-ji.jpg",
    "duration": "7:15",
    "singers": [
        {
            "id": 15,
            "name": "Shabnam Virmani",
            "hindiName": "",
            "primaryOccupation": ""
        }
    ],
    "poets": [
        {
            "id": 13,
            "name": "Kabir",
            "hindiName": "कबीर",
            "primaryOccupation": ""
        }
    ],
    "words": [
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
        }
    ],
    "downloadURL": null,
    "umbrellaTitle": {
        "id": 69,
        "originalTitle": "मन मस्त हुआ फिर क्या बोले?",
        "englishTranslation": "When the mind’s intoxicated",
        "englishTransliteration": "Mann Mast Hua Phir Kya Bole?",
        "category": {
            "id": 8,
            "name": "Umbrella Title",
            "categoryType": "Umbrella Title"
        }
    },
    "songTitle": {
        "id": 68,
        "originalTitle": "मन मस्त हुआ फिर क्या बोले?",
        "englishTranslation": "When the mind’s intoxicated",
        "englishTransliteration": "Mann Mast Hua Phir Kya Bole?",
        "category": {
            "id": 7,
            "name": "Song Title",
            "categoryType": "Song Title"
        }
    },
    "songCategory": {
        "id": 1,
        "name": "Songs",
        "categoryType": "song"
    },
    "mediaCategory": null,
    "reflections": [
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
        }
    ]
};

var song_explore_wordCustomSummaries =
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
            ],
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
                }
            ],
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
        }];

