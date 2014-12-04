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
                                "englishTransliterationTitle": "Kichhu din mone mone",
                                "category":"Song & Reflection",
                                "poet": [
                                     "Sharath",
                                    ],
                                "thumbnailUrl": "imgId",
                                "singers": [
                                      "Parvathy Baul",
                                    ],
                                "duration": "09:11"
                            };

            element = angular.element('<song-mapper details="detailsFromService" custom-style="blah"/>');
            template.put('/user/js/common/templates/mappers/songMapper.html', '<div>{{song.id}} {{song.customStyle}} {{song.imgSrc}} {{song.videoId}} {{song.englishTransliteration}} {{song.category}} {{song.duration}} {{song.singer}} {{song.poet}}</div>');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('song_0 blah imgId  Kichhu din mone mone Song &amp; Reflection 09:11 Parvathy Baul Sharath');
        });
    });
});
