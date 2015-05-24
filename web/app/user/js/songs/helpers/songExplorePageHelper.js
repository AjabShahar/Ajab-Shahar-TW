var AjabShahar = AjabShahar || {};
AjabShahar.SongExploreHelper = (function(){
    var self = {};
    self.createWordThumbnails = function(words){
        var wordThumbnails = [];
        if(!_.isEmpty(words)){
            words.forEach(function(word){
                if(word.publish && word.rootWord){
                    wordThumbnails.push(new AjabShahar.ThumbnailObject(word,"word"));
                }
            });
        }
        return wordThumbnails;
    };
    self.createReflectionThumbnails = function(reflections){
        var reflectionThumbnails = [];
        if(!_.isEmpty(reflections)){
            reflections.forEach(function(reflection){
                if(reflection.published){
                    reflectionThumbnails.push(new AjabShahar.ThumbnailObject(reflection,"reflection"));
                }
            });
        }
        return reflectionThumbnails;
    };

    self.createSongThumbnails = function(songs){
        var songThumbnails = [];
        if(!_.isEmpty(songs)){
            songs.forEach(function(song){
                if(song.publish){
                    songThumbnails.push(new AjabShahar.ThumbnailObject(song,"song"));
                }
            });
        }
        return songThumbnails;
    };

    self.getReflectionsFromWordReflections = function(wordReflections){
        var reflections =_.reduce(wordReflections,function(result,wordReflection){
            var reflectionThumbnails = _.sample(self.createReflectionThumbnails(wordReflection.reflections),3);
            result = result.concat(reflectionThumbnails);
            return result;
        },[]);
        return reflections;
    };

    self.getSongsFromWordReflections = function(wordSongs){
        var songs =_.reduce(wordSongs,function(result,wordSong){
            var songThumbnails = _.sample(self.createSongThumbnails(wordSong.songs),3);
            result = result.concat(songThumbnails);
            return result;
        },[]);
        return songs;
    };

    return self;
})();