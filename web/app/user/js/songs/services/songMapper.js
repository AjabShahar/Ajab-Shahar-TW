thumbnailModule.factory('songMapper', ['wordMapper', function (wordMapper) {
    var getSingers = function (singers) {
        var value = "";

        if (singers.length == 0) return value;

        value = "" + singers[0].name;
        for (var index = 1; index < singers.length; index++) {
            if (index == singers.length - 1)
                value += '& ' + singers[index].name;
            else
                value += ', ' + singers[index].name;
        }

        return value;
    };

    getThumbnails = function (songs, customStyle) {
        return _.reduce(songs, function (thumbnails, song) {
            thumbnails.push({
                "id": song.id,
                "contentId": 'song_' + song.id,
                "isSong": true,
                "englishTranslation": song.songTitle.englishTranslation,
                "customStyle": (customStyle) ? customStyle() : '',
                "englishTransliteration": song.songTitle.englishTransliteration,
                "category": song.category,
                "gathering": _.isEmpty(song.gathering)?"":song.gathering.english,
                "duration": song.duration,
                "singer": (song.singers == null || song.singers.length == 0) ? '' : song.singers[0].name + (song.singers[1] != null ? ' ......' : ''),
                "singers": (song.singers != null && song.singers.length > 1) ? getSingers(song.singers) : '',
                "poet": (song.poets == null || song.poets.length == 0) ? 'Unknown' : song.poets[0].name,
                "thumbnailUrl": song.thumbnailURL,
                "words": wordMapper.getBasicDetails(song.words),
                "singersAsList": song.singers
            });
            return thumbnails;
        }, []);
    };

    getOverviews = function (songs) {
        return _.reduce(songs, function (allIntroductions, song) {
            allIntroductions.push({
                "id": song.id,
                "contentId": "song_" + song.id,
                "isSong": true,
                "videoId": song.youtubeVideoId,
                "englishTranslation": song.songTitle.englishTranslation,
                "englishTransliteration": song.songTitle.englishTransliteration,
                "singer": getSingers(song.singers),
                "audioId": song.soundCloudTrackId,
                "poet": (song.poets == null || song.poets.length == 0) ? 'Unknown' : song.poets[0].name,
                "noun": song.singers.length > 1 ? 'sing' : 'sings',
                "downloadUrl": song.downloadURL,
                "words": wordMapper.getBasicDetails(song.words)
            });

            return allIntroductions;
        }, []);
    };

    getSongDetails = function (songs) {
        return _.reduce(songs, function (details, song) {
            var poet = (Boolean(song.poets[0])) ? song.poets[0].name : '';

            details.push({
                "id": song.id,
                "contentId": "song_" + song.id,
                "videoId": song.youtubeVideoId,
                "audioUrl": song.soundCloudTrackId,
                "singer": getSingers(song.singers),
                "poet": poet,
                "downloadURL": song.downloadURL,
                "about": (song.about == null) ? song.about : '\'' + song.about + '\'',
                "words": wordMapper.getBasicDetails(song.words)
            });
            return details;
        }, []);
    };

    return {
        getThumbnails: getThumbnails,
        getSongDetails: getSongDetails,
        getOverviews: getOverviews
    };
}]);

