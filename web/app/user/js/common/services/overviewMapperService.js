"use strict";

angular.module('utilities').factory('overviewMapperService', [function () {

    var toReflectionOverviews = function (reflections) {
        return _.reduce(reflections, function (overview, reflection) {
            overview.push({
                "id": reflection.id,
                "contentId": "reflection_" + reflection.id,
                "isReflection": true,
                "title": reflection.title,
                "verb": reflection.verb,
                "speaker": reflection.speaker,
                "videoId": reflection.youtubeVideoId,
                "audioUrl": reflection.soundCloudId,
                "text": reflection.reflectionTranscripts,
                "info": reflection.info,
                "people": reflection.people,
                "words": _getWords(reflection.words),
                "songs": reflection.songs
            });
            return overview;
        }, []);
    };

    var toWordOverviews = function (words) {
        return _.reduce(words, function (introductions, word) {
            introductions.push({
                "id": word.id,
                "isWord": true,
                "contentId": 'word_' + word.id,
                "wordOriginal": word.wordOriginal,
                "wordTranslation": word.wordTranslation,
                "wordTransliteration": word.wordTransliteration,
                "hindiIntroExcerpt": word.hindiIntroExcerpt,
                "englishIntroExcerpt": word.englishIntroExcerpt,
                "wordIntroEnglish": word.wordIntroEnglish,
                "writers": word.writers,
                "displayAjabShaharTeam": word.displayAjabShaharTeam
            });
            return introductions;
        }, []);
    };

    var toSongOverviews = function (songs) {
        return _.reduce(songs, function (overviews, song) {
            overviews.push({
                "id": song.id,
                "contentId": "song_" + song.id,
                "isSong": true,
                "videoId": song.youtubeVideoId,
                "englishTranslation": song.songTitle.englishTranslation,
                "englishTransliteration": song.songTitle.englishTransliteration,
                "singers": _getSingers(song.singers),
                "audioId": song.soundCloudTrackId,
                "poet": (song.poets == null || song.poets.length == 0) ? 'Unknown' : song.poets[0],
                "noun": song.singers.length > 1 ? 'sing' : 'sings',
                "downloadUrl": song.downloadURL,
                "words": _getWords(song.words)
            });

            return overviews;
        }, []);
    };

    var _getWords = function (words) {
        return _.reduce(words, function (wordsList, word) {
            if (word.rootWord && word.publish) {
                wordsList.push({
                    "id": word.id,
                    "translation": word.wordTranslation,
                    "transliteration": word.wordTransliteration
                });
            }
            return wordsList;
        }, []);
    };

    var _getSingers = function (singers) {
        return _.reduce(singers,function(singersList,singer){
            singersList.push({
                "id": singer.id,
                "name":singer.name
            });
            return singersList;
        },[]);

        //var value = "";
        //
        //if (singers.length == 0) return value;
        //
        //value = "" + singers[0].name;
        //for (var index = 1; index < singers.length; index++) {
        //    if (index == singers.length - 1)
        //        value += '& ' + singers[index].name;
        //    else
        //        value += ', ' + singers[index].name;
        //}
        //
        //return value;
    };


    return{
        toSongOverviews:toSongOverviews,
        toWordOverviews:toWordOverviews,
        toReflectionOverviews:toReflectionOverviews
    }
}]);
