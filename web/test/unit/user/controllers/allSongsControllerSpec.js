'use strict';

describe('On All songs page', function() {
    var scope, $httpBackend;

    var criteriaList = Ajabshahar.user.SongFilterConfig.filterCategories;

    var singerCategory = criteriaList[0];
    var wordCategory = criteriaList[2];

    var singerName = "Mooralala Marwada";
    var wordName = "Akath Katha";

    beforeEach(module("allSongsApp"));

    beforeEach(inject(function (_$controller_, _$rootScope_, _$window_, _songsContentService_, _songMapper_, _$httpBackend_, sortService) {
        scope = _$rootScope_.$new();
        $httpBackend = _$httpBackend_;

        _$controller_('allSongsController', {
            $scope: scope,
            $window: _$window_,
            $rootScope: _$rootScope_,
            songsContentService: _songsContentService_,
            songMapper:_songMapper_,
            sortService:sortService
        });

        var resetCategories = function(category){
            category.value = "";
            category.disabled = undefined;
            category.active = undefined;
        };
        $httpBackend.when("GET", "/api/songs/getPublishedSongs").respond(test_AllSongsList);
        resetCategories(singerCategory);
        resetCategories(wordCategory);
    }));


    describe('When its initialized', function() {
        it(' should setup filter categories and mark all the categories that have 0 items to filter on as disabled', function() {
            $httpBackend.flush();

            expect(scope.filterItems.Word.length).toBe(3);
            expect(scope.filterItems.Singer.length).toBe(6);
            expect(scope.filterItems.Poet.length).toBe(3);
            expect(scope.filterItems.Gathering.length).toBe(1);
            expect(scope.filterItems.Gathering[0][0]).toBe("rajasthan");
        });

    });

    describe('When user interacts with filter pardas then', function () {


        it('clicking a category in filter parda 1, should mark the category as currently active if it not already selected and mark other categories as active=false', function () {
            $httpBackend.flush();

            scope.filterCategoryClicked(singerCategory);
            expect(singerCategory.active).toBeTruthy();

            scope.filterCategoryClicked(wordCategory);
            expect(wordCategory.active).toBeTruthy();
            expect(singerCategory.active).toBeFalsy();

        });

        it('selecting a singer(or any other category) should mark the category as disabled', function () {

            $httpBackend.flush();

            scope.filterCategoryClicked(singerCategory);
            expect(scope.openSecondParda).toBeTruthy();
            scope.filterItemSelected(singerName);

            expect(singerCategory.active).toBeFalsy();
            expect(singerCategory.disabled).toBeTruthy();
            expect(scope.openSecondParda).toBeFalsy();

        });


        it('selecting a singer(or any other category) and a word(or any other category) should mark both the categories as disabled', function () {
            $httpBackend.flush();


            //select a singer
            scope.filterCategoryClicked(singerCategory);
            expect(scope.openSecondParda).toBeTruthy();
            scope.filterItemSelected(singerName);
            expect(singerCategory.active).toBeFalsy();
            expect(scope.openSecondParda).toBeFalsy();

            //select a word
            scope.filterCategoryClicked(wordCategory);
            expect(scope.openSecondParda).toBeTruthy();
            scope.filterItemSelected(wordName);
            expect(wordCategory.active).toBeFalsy();
            expect(scope.openSecondParda).toBeFalsy();

            //check if both are disabled
            expect(singerCategory.disabled).toBeTruthy();
            expect(wordCategory.disabled).toBeTruthy();
        });
        it('deselecting a selected singer(or any other category) should mark the category as disabled=false', function () {
            $httpBackend.flush();

            //select a singer and a word
            scope.filterCategoryClicked(singerCategory);
            scope.filterItemSelected(singerName);

            scope.filterCategoryClicked(wordCategory);
            scope.filterItemSelected(wordName);

            //check if both are disabled
            expect(singerCategory.disabled).toBeTruthy();
            expect(wordCategory.disabled).toBeTruthy();

            //remove the word filter
            scope.removeFilterCriteria(wordCategory);

            //check if enabled
            expect(wordCategory.disabled).toBeFalsy();

        });
        it('selecting a singer(or any other category) should mark all other categories that dont have any values to filter on as disabled', function () {

        });
        it('clicking on reset all should remove all the filters from the filter parda 1 and display all songs', function () {

        })


    })
});


var test_AllSongsList = {
    songs: [
        {
            "id": 11,
            "umbrellaTitle": {
                "id": 38,
                "originalTitle": "भजन रो गुड़क रहयो गाड़ो",
                "englishTransliteration": "Bhajan Ro Gudak Rahyo Gaado",
                "englishTranslation": "The Cart of Meditation is Tottering"
            },
            "songTitle": {
                "id": 50,
                "originalTitle": "नित खैर मंगा",
                "englishTransliteration": "Nit Khair Manga",
                "englishTranslation": "Everyday I Ask For Your Well Being"
            },
            "publish": true,
            "category": "Songs",
            "featured": true,
            "youTubeVideoId": "",
            "soundCloudTrackId": "https://soundcloud.com/kashanilyas/we-main-chori-chori-tere-nal/sets",
            "thumbnailUrl": "https://farm8.staticflickr.com/7583/16097980187_72dfa07068_o.png",
            "duration": "07:45",
            "gathering":{
                id:11,
                english:"rajasthan",
                hindi:"rajasthan"
            },
            "singers": [
                {
                    "id": 25,
                    "name": "Mukhtiyar Ali",
                    "hindiName": ""
                },
                {
                    "id": 10,
                    "name": "Mooralala Marwada",
                    "hindiName": ""
                }
            ],
            "poets": [
                {
                    "id": 26,
                    "name": "Badar Muneer",
                    "hindiName": ""
                }
            ],
            "words": [
                {
                    "id": 1,
                    "wordOriginal": "अकथ कथा",
                    "wordTranslation": "Akath Katha",
                    "wordTransliteration": "Untellable Tale",
                    "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told?",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": false,
                    "publish": true
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
                    "rootWord": true,
                    "publish": false
                }
            ]
        },
        {
            "id": 12,
            "umbrellaTitle": {
                "id": 57,
                "originalTitle": "थारा रंगमहल में",
                "englishTransliteration": "Thaara Rangmahal Mein",
                "englishTranslation": "In Your Colourful Palace"
            },
            "songTitle": {
                "id": 56,
                "originalTitle": "थारा रंगमहल में",
                "englishTransliteration": "Thaara Rangmahal Mein",
                "englishTranslation": "In Your Colourful Palace"
            },
            "publish": true,
            "category": "Songs",
            "featured": true,
            "gathering":{
                id:0,
                english:null,
                hindi:null
            },
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
            "poets": [
                {
                    "id": 11,
                    "name": "Kabir",
                    "hindiName": ""
                }
            ],
            "words": []
        },
        {
            "id": 5,
            "umbrellaTitle": {
                "id": 36,
                "originalTitle": "हिए काया में",
                "englishTransliteration": "Hiye Kaaya Mein",
                "englishTranslation": "In This Body"
            },
            "songTitle": {
                "id": 35,
                "originalTitle": "हिए काया में",
                "englishTransliteration": "Hiye Kaaya Mein",
                "englishTranslation": "In This Body"
            },
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
            "poets": [
                {
                    "id": 24,
                    "name": "Shah Abdul Latif Bhitai",
                    "hindiName": ""
                }
            ],
            "words": [
                {
                    "id": 5,
                    "wordOriginal": "जागना सोना",
                    "wordTranslation": "Waking Sleeping",
                    "wordTransliteration": "Jaagna Sona",
                    "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": true,
                    "publish": true
                }
            ]
        },
        {
            "id": 8,
            "umbrellaTitle": {
                "id": 36,
                "originalTitle": "हिए काया में",
                "englishTransliteration": "Hiye Kaaya Mein",
                "englishTranslation": "In This Body"
            },
            "songTitle": {
                "id": 35,
                "originalTitle": "हिए काया में",
                "englishTransliteration": "Hiye Kaaya Mein",
                "englishTranslation": "In This Body"
            },
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
            "poets": [
                {
                    "id": 11,
                    "name": "Kabir",
                    "hindiName": ""
                }
            ],
            "words": [
                {
                    "id": 1,
                    "wordOriginal": "अकथ कथा",
                    "wordTranslation": "Akath Katha",
                    "wordTransliteration": "Untellable Tale",
                    "hindiIntroExcerpt": "Akath means inexpressible, indescribable, ineffable. Beyond words. Beyond language. Beyond mind. But why all the hue and cry if the tale cannot be told?",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": false,
                    "publish": false
                },
                {
                    "id": 5,
                    "wordOriginal": "जागना सोना",
                    "wordTranslation": "Waking Sleeping",
                    "wordTransliteration": "Jaagna Sona",
                    "hindiIntroExcerpt": "A story tells of how, when the Buddha attained illumination, somebody asked him: ‘So, are you enlightened now?’ The Buddha’s response was: ‘I am awake.’",
                    "englishIntroExcerpt": "",
                    "writers": [],
                    "rootWord": true,
                    "publish": true
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
                    "rootWord": true,
                    "publish":true
                }
            ]
        }
    ]
};
