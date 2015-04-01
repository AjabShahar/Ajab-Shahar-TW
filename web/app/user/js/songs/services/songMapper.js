thumbnailModule.factory('songMapper',['wordMapper',function(wordMapper){
    var getSingers = function(singers){
        var value = "";

        if(singers.length == 0) return value;

        value = "" + singers[0].name;
         for(var index=1;index< singers.length;index++){
            if(index==singers.length-1)
                value += '& '+singers[index].name;
            else
                value += ', '+singers[index].name;
        } 
       
       return value;
    };

    getThumbnails = function(songs,customStyle) {
        return _.reduce(songs,function(thumbnails, song,index) {
            thumbnails.push({
                "id":song.id,
                "contentId":'song_'+song.id,
                "isSong":true,
                "englishTranslation":song.englishTranslationTitle,
                "customStyle": (customStyle)? customStyle():'',
                "englishTransliteration":song.englishTransliterationTitle,
                "category":song.category,
                "duration":song.duration,
                "singer":(song.singers==null || song.singers.length==0) ? '': song.singers[0].name +(song.singers[1]!=null ? ' ......':''),
                "singers":(song.singers!=null && song.singers.length > 1)? getSingers(song.singers):'',
                "poet":(song.poet==null || song.poet.length==0)?'Unknown': song.poet[0].name,
                "thumbnailUrl":song.thumbnailUrl,
                "words": wordMapper.getBasicDetails(song.words.words),
                "singersAsList":song.singers
            });
            return thumbnails;
        },[]);
    };

    getOverviews = function(songs,customStyle) {
        return _.reduce(songs,function(allIntroductions, song,index) {
            allIntroductions.push({"id":song.id,
                "contentId":"song_"+song.id,
                "isSong":true,
                "videoId":song.youTubeVideoId,
                "englishTranslation":song.englishTranslationTitle,
                "englishTransliteration":song.englishTransliterationTitle,
                "singer":getSingers(song.singers),
                "audioId":song.soundCloudTrackId,
                "poet":(song.poet==null || song.poet.length==0)?'Unknown': song.poet[0].name,
                "noun": song.singers.length>1? 'sing':'sings',
                "downloadUrl":song.downloadUrl,
                "words": wordMapper.getBasicDetails(song.words.words)});

            return allIntroductions;
        },[]);
    };

    getSongDetails = function(songs){
        return _.reduce(songs,function(details, song,index) {
            var poet = (Boolean(song.poet[0])) ? song.poet[0].name : '';

            details.push({"id":song.id,
            "contentId":"song_"+song.id,
            "videoId":song.youTubeVideoId,
            "audioUrl":song.soundCloudTrackId,
            "singer":getSingers(song.singers),
            "poet": poet,
            "downloadURL":song.downloadUrl,
            "about": (song.about == null)? song.about :'\'' + song.about + '\'',
            "words": wordMapper.getBasicDetails(song.words.words)
            });
            return details;
        },[]);
    };

    return {
        getThumbnails: getThumbnails,
        getSongDetails: getSongDetails,
        getOverviews:getOverviews
    };
}]);

