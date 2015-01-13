//'use strict';
//
//describe('Mapper', function() {
//    describe('song mapper', function() {
//        var songThumbnailMapper;
//        beforeEach(function(){
//            module('thumbnailModule');
//        });
//
//        beforeEach(inject(function(songThumbnailMapper) {
//            songThumbnailMapper = songThumbnailMapper;
//        }));
//
//        it('Should map song with details from service', function() {
//            var songs = songThumbnailMapper.getSongs([{
//                                                                          "id":0,
//                                                                          "englishTransliterationTitle": "Kichhu din mone mone",
//                                                                          "category":"Song & Reflection",
//                                                                          "poet": [
//                                                                              {
//                                                                                 "name":"Sharath"
//                                                                              }
//                                                                          ],
//                                                                          "thumbnailUrl": "imgId",
//                                                                          "singers": [
//                                                                                {
//                                                                                   "name":"Parvathy Baul"
//                                                                                }
//                                                                                ,
//                                                                              ],
//                                                                          "duration": "09:11"
//                                                                      }]);
//
//            expect(songs.length.toBe(1));
//            expect(songs[0].id.toBe(0));
//            expect(songs[0].contentId.toBe(song_0));
//            expect(songs[0].thumbnailUrl.toBe('imgId'));
//            expect(songs[0].englishTransliteration.toBe('Kichhu din mone mone'));
//            expect(songs[0].category.toBe('Song &amp; Reflection'));
//            expect(songs[0].duration.toBe('09:11'));
//            expect(songs[0].singer.toBe('Parvathy Baul'));
//            expect(songs[0].poet.toBe('Sharath'));
//        });
//    });
//});
