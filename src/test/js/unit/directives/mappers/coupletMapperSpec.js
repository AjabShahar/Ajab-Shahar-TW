'use strict';

describe('Mapper', function() {

    describe('couplet mapper', function() {
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
                                        "contentType":"Couplets",
                                        "category":{
                                         "name":"Couplet",
                                        },
                                        "englishTranslationText":"A pot in water, water in a pot",
                                        "thumbnail_url":"http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG",
                            };

            element = angular.element('<couplet-mapper details="detailsFromService" custom-style="blah"/>');
            template.put('/user/js/common/templates/mappers/coupletMapper.html', '{{couplet.id}} {{customStyle}} {{couplet.englishTranslationText}} {{couplet.categoryName}} {{couplet.imgSrc}}');
            compile(element)(scope);
            scope.$apply();

            expect(element.html()).toBe('couplet_0 blah A pot in water, water in a pot Couplet http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG');
        });
    });
});
