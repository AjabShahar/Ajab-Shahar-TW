'use strict';

describe('Mapper', function() {
    describe('song mapper', function() {
        var scope;
        var element;
        var compile;
        var template;
        beforeEach(function(){
            module('htmlGenerator');
            module('thumbnailModule');
        });

        beforeEach(inject(function($rootScope,$compile,$templateCache) {
            scope = $rootScope.$new();
            compile = $compile;
            template = $templateCache;
        }));

        it('Should map song with details from service', function() {
            scope.detailsFromService={
                                "id":0,
                                "songCategory": {"name":"Song & Reflection"},
                                "songTitle":{
                                    "englishTranslation": "Practice the art of dying",
                                },
                                "poets": [
                                    {
                                    "category": "POET",
                                    "firstName": "Sharath",
                                    "middleName": "",
                                    "lastName": ""
                                    }
                                    ],

                                "youtubeVideoId": "videoId",
                                "thumbnail_url": "imgId",
                                "singers": [
                                    {
                                    "category": "SINGER",
                                    "firstName": "Parvathy",
                                    "middleName": "",
                                    "lastName": "Baul"
                                    }
                                    ],
                                "duration": "09:11"
                            };

            element = angular.element('<song-mapper details="detailsFromService" custom-style="blah"/>');
            template.put('/user/js/common/templates/mappers/songMapper.html', '{{song.id}} {{song.customStyle}} {{song.imgSrc}} {{song.videoId}} {{song.englishTranslation}} {{song.categoryName}} {{song.duration}} {{song.singer}} {{song.poet}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('song_0 blah imgId videoId Practice the art of dying Song &amp; Reflection 09:11 Parvathy Baul Sharath');
        });
    });
});
