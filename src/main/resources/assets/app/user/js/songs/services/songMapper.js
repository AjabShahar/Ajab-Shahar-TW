var songMapper = function ($http) {
    var getSingers = function(singers){
       var value = "" + singers[0].name;
         for(var index=1;index< singers.length;index++){
            if(index==singers.length-1)
                value += '& '+singers[index].name;
            else
                value += ', '+singers[index].name;
         }
       return value;
    };

    getSongs = function(songs,customStyle) {
        return _.reduce(songs,function(thumbnails, song,index) {
            thumbnails.push({"id":song.id,
            "contentId":'song_'+song.id,
            "englishTranslation":song.englishTranslationTitle,
            "customStyle": (customStyle)? customStyle(song):'',
            "englishTransliteration":song.englishTransliterationTitle,
            "category":song.category,
            "duration":song.duration,
            "singer":(song.singers==null || song.singers.length==0) ? '': song.singers[0].name +(song.singers[1]!=null ? ' ......':''),
            "singers":(song.singers!=null && song.singers.length > 1)? getSingers(song.singers):'',
            "poet":(song.poet==null || song.poet.length==0)?'Unknown': song.poet[0].name,
            "thumbnailUrl":song.thumbnailUrl});
            return thumbnails;
        },[]);
    };

    getSongDetails = function(songs){
        return _.reduce(songs,function(details, song,index) {
            details.push({"id":song.id,
            "contentId":"song_"+song.id,
            "videoId":song.youtubeVideoId,
            "audioUrl":song.soundCloudTrackID,
            "singer":getSingers(song.singers),
            "poet":song.poet[0].name,
            "downloadURL":song.downloadUrl,
            "about": '\'' + song.about + '\''});
            return details;
        },[]);
    }

    return {
        getSongs: getSongs,
        getSongDetails: getSongDetails
    };
};

thumbnailModule.factory('songMapper', [songMapper]);