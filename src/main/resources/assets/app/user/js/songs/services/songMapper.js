var songMapper = function () {
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

    var getThumbnails = function(songs,customStyle) {
        return _.reduce(songs,function(thumbnails, song,index) {
            thumbnails.push({"id":song.id,
            "contentId":'song_'+song.id,
            "englishTranslation":song.englishTranslationTitle,
            "customStyle": (customStyle)? customStyle():'',
            "englishTransliteration":song.englishTransliterationTitle,
            "category":song.category,
            "duration":song.duration,
            "singer":(song.singers==null || song.singers.length==0) ? '': song.singers[0].name +(song.singers[1]!=null ? ' ......':''),
            "singers":(song.singers!=null && song.singers.length > 1)? getSingers(song.singers):'',
            "poet":(song.poet==null || song.poet.length==0)?'Unknown': song.poet[0].name,
            "thumbnailUrl":song.thumbnailUrl,
            "searchableCriteria":{
                               "singers":song.singers,
                               "poets":song.poet,
                            }});
            return thumbnails;
        },[]);
    };

    getIntroductions = function(songs,customStyle) {
        return _.reduce(songs,function(allIntroductions, song,index) {
            allIntroductions.push({"id":song.id,
                "contentId":"song_"+song.id,
                "videoId":song.youTubeVideoId,
                "englishTranslation":song.englishTranslationTitle,
                "englishTransliteration":song.englishTransliterationTitle,
                "singer":getSingers(song.singers),
                "audioId":song.soundCloudTrackId,
                "poet":(song.poet==null || song.poet.length==0)?'Unknown': song.poet[0].name,
                "noun": song.singers.length>1? 'sing':'sings'});

            return allIntroductions;
        },[]);
    };

    var getSongDetails = function(songs){
        return _.reduce(songs,function(details, song,index) {
            var poet = (Boolean(song.poet[0])) ? song.poet[0].name : '';

            details.push({"id":song.id,
            "contentId":"song_"+song.id,
            "videoId":song.youTubeVideoId,
            "audioUrl":song.soundCloudTrackId,
            "singer":getSingers(song.singers),
            "poet": poet,
            "downloadURL":song.downloadUrl,
            "about": '\'' + song.about + '\''});
            return details;
        },[]);
    };

    var getSongs = function(songs,customStyle){
        return _.reduce(songs,function(thumbnails, song,index) {
            thumbnails.push({"id":song.id,
                "contentId":'song_'+song.id,
                "englishTranslation":song.englishTranslationTitle,
                "customStyle": (customStyle)? customStyle():'',
                "englishTransliteration":song.englishTransliterationTitle,
                "category":song.category,
                "duration":song.duration,
                "singer":(song.singers==null || song.singers.length==0) ? '': song.singers[0].name +(song.singers[1]!=null ? ' ......':''),
                "singers":(song.singers!=null && song.singers.length > 1)? getSingers(song.singers):'',
                "poet":(song.poet==null || song.poet.length==0)?'Unknown': song.poet[0].name,
                "thumbnailUrl":song.thumbnailUrl,
                "videoId":song.youTubeVideoId,
                "audioUrl":song.soundCloudTrackId,
                "downloadURL":song.downloadUrl,
                "about": '\'' + song.about + '\'',
                "searchableCriteria":{
                    "singers":song.singers,
                    "poets":song.poet
                }});
            
            return thumbnails;
        },[]);
        
    };
    return {
        getThumbnails: getThumbnails,
        getSongDetails: getSongDetails,
        getIntroductions:getIntroductions,
        getSongs:getSongs
    };
};

thumbnailModule.factory('songMapper', [songMapper]);