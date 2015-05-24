'use strict';

describe('Mapper', function () {
    describe('song mapper', function () {
        var songMapper;
        var songList;
        beforeEach(function () {
            module('thumbnailModule');
            songList = [{
                "id": 1,
                "songTitle": {
                    "englishTranslation": "For a few days,O heart",
                    "englishTransliteration": "Kichhu din mone mone"
                },
                "songCategory": {name: "Song & Reflection"},
                "poets": [{
                    "name": "Sharath"
                }],
                "thumbnailURL": "imgId",
                "singers": [{
                    "name": "Parvathy Baul"
                }],
                "duration": "09:11",
                "words": [],
                "youtubeVideoId": "123456",
                "soundCloudTrackId": "sound cloud track",
                "about": "about song",
                "downloadURL": "download link"
            }];
        });

        beforeEach(inject(function (_songMapper_) {
            songMapper = _songMapper_;
        }));

        it('Should get song thumbnail representations', function () {
            var songs = songMapper.getThumbnails(songList);
            expect(songs.length).toBe(1);
            expect(songs[0].id).toBe(1);
            expect(songs[0].contentId).toBe('song_1');
            expect(songs[0].thumbnailUrl).toBe('imgId');
            expect(songs[0].englishTransliteration).toBe('Kichhu din mone mone');
            expect(songs[0].category).toBe('Song & Reflection');
            expect(songs[0].duration).toBe('09:11');
            expect(songs[0].singer).toBe('Parvathy Baul');
            expect(songs[0].poet).toBe('Sharath');
        });

        it('Should get song overviews', function () {
            var songs = songMapper.getOverviews(songList);
            expect(songs.length).toBe(1);
            expect(songs[0].id).toBe(1);
            expect(songs[0].contentId).toBe('song_1');
            expect(songs[0].englishTransliteration).toBe('Kichhu din mone mone');
            expect(songs[0].englishTranslation).toBe('For a few days,O heart');
            expect(songs[0].videoId).toBe('123456');
            expect(songs[0].audioId).toBe('sound cloud track');
            expect(songs[0].downloadUrl).toBe('download link');
            expect(songs[0].singer).toBe('Parvathy Baul');
            expect(songs[0].noun).toBe('sings');
            expect(songs[0].poet).toBe('Sharath');
            expect(songs[0].words.length).toBe(0);
        });

        it('Should get song details', function () {
            var songs = songMapper.getSongDetails(songList);

            expect(songs.length).toBe(1);
            expect(songs[0].id).toBe(1);
            expect(songs[0].contentId).toBe('song_1');
            expect(songs[0].videoId).toBe('123456');
            expect(songs[0].audioUrl).toBe('sound cloud track');
            expect(songs[0].singer).toBe('Parvathy Baul');
            expect(songs[0].poet).toBe('Sharath');
            expect(songs[0].about).toBe('\'about song\'');
            expect(songs[0].words.length).toBe(0);
        });
    });
});
