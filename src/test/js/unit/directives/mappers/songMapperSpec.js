//'use strict';
//
//describe('Mapper', function() {
//
//    describe('song mapper', function() {
//        var scope;
//        var element;
//        var compile;
//        var template;
//        beforeEach(function(){
//            module('thumbnailModule');
//        });
//
//        beforeEach(inject(function($rootScope,$compile,$templateCache) {
//            scope = $rootScope.$new();
//            compile = $compile;
//            template = $templateCache;
//        }));
//
//        it('Should map song with details from service', function() {
//            scope.detailsFromService={
//                "id":"id1",
//                "thumbnail_url":"thumbnail_url1",
//                "youtubeVideoId":"youtubeVideoId1",
//                "songTitle":{
//                    "englishTranslation":"englishTranslation1"
//                },
//                "songCategory":{
//                    "name":"name1"
//                },
//                "duration":"duration1",
//                "singers":{
//                    "firstName":"fName1",
//                    "middleName":"mName1",
//                },
//                "poets":{
//                    "firstName":"fName2",
//                    "middleName":"mName2",
//                },
//            };
//
//            element = angular.element('<song-mapper details="detailsFromService" custom-style="blah"/>');
//            template.put('/user/js/common/templates/mappers/songMapper.html', '{{song.id}}" {{song.customStyle}} {{song.imgSrc}} {{song.videoId}} {{song.englishTranslation}} {{song.categoryName}} {{song.duration}} {{song.singer}} {{song.poet}}');
//            compile(element)(scope);
//            scope.$apply();
//
//            expect(element.html()).toBe('id1 blah thumbnail_url1 youtubeVideoId1 englishTranslation1 name1 duration1 fName1 mName1 fName2 mName2');
//        });
//    });
//});
