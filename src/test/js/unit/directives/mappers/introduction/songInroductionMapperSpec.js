'use strict';

describe('Mapper', function() {

    describe("song Mapper Specs", function(){
        var service;
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

        it("should get popup for songs", function(){
            scope.detailsFromService=
                    {
                        "id":2,
                        "contentType": "Songs",
                        "categoryName": "Song & Reflection",
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
                        "soundCloudTrackID": "scId",
                        "imageUrl": "imgId",
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

            element = angular.element('<song-introduction-mapper details="detailsFromService" show-details-service="detailsService"/>');
            template.put('/user/js/common/templates/mappers/introduction/songIntroductionMapper.html', '<div>{{song.id}} {{song.singer}} {{song.poet}} {{song.title}} {{song.audioId}} {{song.videoId}}</div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('<div class="ng-binding">song_2 Parvathy Baul Sharath  scId videoId</div>');
        });

    });
});