'use strict';

describe("Song content service", function () {
    var scope,
        songMapper,
        $httpBackend,
        songsContentService;


    beforeEach(module("allSongsApp"));

    beforeEach(inject(function (_songsContentService_, _$httpBackend_, _songMapper_) {
        $httpBackend = _$httpBackend_;
        songsContentService = _songsContentService_;
        songMapper = _songMapper_;
    }));

    it("should extract words from list of songs", function () {
        var songsList = songMapper.getThumbnails(test_songsList, null);
        expect(songsList.length).toBe(4);

        var wordsInSongs = songsContentService.getWordsFrom(songsList);
        expect(wordsInSongs.length).toBe(4);
        var expectedList = ["bazaar","Jaagna Sona","Gitara","Akath Katha"];
        wordsInSongs.forEach(function(words){
            _.contains(expectedList,words[0]);
        });
    });

    it("should extract singers from list of songs", function () {
        var songsList = songMapper.getThumbnails(test_songsList, null);
        expect(songsList.length).toBe(4);

        var singersInSongs = songsContentService.getSingersFrom(songsList);
        expect(singersInSongs.length).toBe(6);
        var expectedList = ["Sumar Kadu Jat","Mooralala Marwada","Prahlad Singh Tipanya","Mukhtiyar Ali","Kaluram Bamaniya","Parvathy Baul"];
        singersInSongs.forEach(function(singer){
            _.contains(expectedList,singer[0]);
        });
    });

    it("should extract poets from list of songs", function () {
        var songsList = songMapper.getThumbnails(test_songsList, null);
        expect(songsList.length).toBe(4);

        var poetsInSongs = songsContentService.getPoetsFrom(songsList);
        expect(poetsInSongs.length).toBe(3);
        var expectedList = ["Shah Abdul Latif Bhitai","Kabir","Badar Muneer"];
        poetsInSongs.forEach(function(poet){
            _.contains(expectedList,poet[0]);
        });
    });
});

var test_songsList = [
    {
        "id": 11,
        "umbrellaTitleId": 38,
        "umbrellaTitleOriginal": "भजन रो गुड़क रहयो गाड़ो",
        "umbrellaTitleEnglishTransliteration": "Bhajan Ro Gudak Rahyo Gaado",
        "umbrellaTitleEnglishTranslation": "The Cart of Meditation is Tottering",
        "titleId": 50,
        "titleOriginal": "नित खैर मंगा",
        "englishTransliterationTitle": "Nit Khair Manga",
        "englishTranslationTitle": "Everyday I Ask For Your Well Being",
        "publish": true,
        "category": "Songs",
        "featured": true,
        "youTubeVideoId": "",
        "soundCloudTrackId": "https://soundcloud.com/kashanilyas/we-main-chori-chori-tere-nal/sets",
        "thumbnailUrl": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png",
        "duration": "07:45",
        "singers": [
            {
                "id": 25,
                "name": "Mukhtiyar Ali",
                "hindiName": ""
            }
        ],
        "poet": [
            {
                "id": 26,
                "name": "Badar Muneer",
                "hindiName": ""
            }
        ],
        "words": {
            "words": [
                {
                    "id": 1,
                    "wordOriginal": "अकथ कथा",
                    "wordTranslation": "Akath Katha",
                    "wordTransliteration": "Untellable Tale",
                    "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told?",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": false
                },
                {
                    "id": 14,
                    "wordOriginal": "Guitar",
                    "wordTranslation": "Guitar",
                    "wordTransliteration": "Gitara",
                    "hindiIntroExcerpt": "This is english intro excerpt",
                    "englishIntroExcerpt": "This is hindi intro excerpt",
                    "writers": [
                        {
                            "id": 7,
                            "name": "Parvathy Baul",
                            "hindiName": ""
                        }
                    ],
                    "rootWord": true
                }
            ]
        }
    },
    {
        "id": 12,
        "umbrellaTitleId": 57,
        "umbrellaTitleOriginal": "थारा रंगमहल में",
        "umbrellaTitleEnglishTransliteration": "Thaara Rangmahal Mein",
        "umbrellaTitleEnglishTranslation": "In Your Colourful Palace",
        "titleId": 56,
        "titleOriginal": "थारा रंगमहल में",
        "englishTransliterationTitle": "Thaara Rangmahal Mein",
        "englishTranslationTitle": "In Your Colourful Palace",
        "publish": true,
        "category": "Songs",
        "featured": true,
        "youTubeVideoId": "sop02Ivqji0",
        "soundCloudTrackId": "https://soundcloud.com/kashanilyas/we-main-chori-chori-tere-nal/sets",
        "thumbnailUrl": "https://farm8.staticflickr.com/7511/16096481990_31028b8c63_o.jpg",
        "duration": "06:45",
        "singers": [
            {
                "id": 12,
                "name": "Prahlad Singh Tipanya",
                "hindiName": ""
            }
        ],
        "poet": [
            {
                "id": 11,
                "name": "Kabir",
                "hindiName": ""
            }
        ],
        "words": {
            "words": []
        }
    },
    {
        "id": 5,
        "umbrellaTitleId": 36,
        "umbrellaTitleOriginal": "हिए काया में",
        "umbrellaTitleEnglishTransliteration": "Hiye Kaaya Mein",
        "umbrellaTitleEnglishTranslation": "In This Body",
        "titleId": 35,
        "titleOriginal": "हिए काया में",
        "englishTransliterationTitle": "Hiye Kaaya Mein",
        "englishTranslationTitle": "In This Body",
        "publish": true,
        "category": "Songs",
        "featured": true,
        "youTubeVideoId": "J4IU5tDlD_s",
        "soundCloudTrackId": "174024475",
        "thumbnailUrl": "https://farm8.staticflickr.com/7513/16257916986_baf4e108cb_o.jpg",
        "duration": "07:05",
        "singers": [
            {
                "id": 10,
                "name": "Mooralala Marwada",
                "hindiName": ""
            },
            {
                "id": 21,
                "name": "Sumar Kadu Jat",
                "hindiName": ""
            },
            {
                "id": 27,
                "name": "Parvathy Baul",
                "hindiName": ""
            }
        ],
        "poet": [
            {
                "id": 24,
                "name": "Shah Abdul Latif Bhitai",
                "hindiName": ""
            }
        ],
        "words": {
            "words": [
                {
                    "id": 5,
                    "wordOriginal": "जागना सोना",
                    "wordTranslation": "Waking Sleeping",
                    "wordTransliteration": "Jaagna Sona",
                    "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": true
                }
            ]
        }
    },
    {
        "id": 8,
        "umbrellaTitleId": 36,
        "umbrellaTitleOriginal": "हिए काया में",
        "umbrellaTitleEnglishTransliteration": "Hiye Kaaya Mein",
        "umbrellaTitleEnglishTranslation": "In This Body",
        "titleId": 35,
        "titleOriginal": "हिए काया में",
        "englishTransliterationTitle": "Hiye Kaaya Mein",
        "englishTranslationTitle": "In This Body",
        "publish": true,
        "category": "Songs",
        "featured": true,
        "youTubeVideoId": "J4IU5tDlD_s",
        "soundCloudTrackId": "174024475",
        "thumbnailUrl": "https://farm9.staticflickr.com/8632/15663990083_cac15c4210_o.jpg",
        "duration": "04.55",
        "singers": [
            {
                "id": 32,
                "name": "Kaluram Bamaniya",
                "hindiName": ""
            }
        ],
        "poet": [
            {
                "id": 11,
                "name": "Kabir",
                "hindiName": ""
            }
        ],
        "links": [
            {
                "rel": "singer",
                "url": "/api/people/32"
            },
            {
                "rel": "poet",
                "url": "/api/people/11"
            }
        ],
        "words": {
            "words": [
                {
                    "id": 1,
                    "wordOriginal": "अकथ कथा",
                    "wordTranslation": "Akath Katha",
                    "wordTransliteration": "Untellable Tale",
                    "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told?",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": false
                },
                {
                    "id": 5,
                    "wordOriginal": "जागना सोना",
                    "wordTranslation": "Waking Sleeping",
                    "wordTransliteration": "Jaagna Sona",
                    "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": false
                },
                {
                    "id": 5,
                    "wordOriginal": "जागना सोना",
                    "wordTranslation": "Waking Sleeping",
                    "wordTransliteration": "Jaagna Sona",
                    "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": true
                },
                {
                    "id": 10,
                    "wordOriginal": "bazaar",
                    "wordTranslation": "market",
                    "wordTransliteration": "bazaar",
                    "hindiIntroExcerpt": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    "englishIntroExcerpt": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    "writers": [
                        {
                            "id": 7,
                            "name": "Parvathy Baul",
                            "hindiName": ""
                        }
                    ],
                    "rootWord": false
                }
            ]
        }
    }
];
