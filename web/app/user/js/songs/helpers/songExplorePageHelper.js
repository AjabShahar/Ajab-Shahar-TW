"use strict";

AjabShahar = AjabShahar || {};
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

    var getUnique = function(newItems,existingItems ){
        var uniqueItems= newItems || [];
        if(!_.isEmpty(existingItems) && !_.isEmpty(newItems)){
            uniqueItems = _.reject(newItems,function(newItem){
                return _.some(existingItems,function(existingItem){
                    return existingItem ? newItem.id === existingItem.id:false;
                })
            });
        }
        return uniqueItems;
    };

    self.getReflectionsFromWordReflections = function(wordReflections,existingReflections){
        var reflections =_.reduce(wordReflections,function(result,wordReflection){
            var reflectionsRelatedToWords =[];
            if(!_.isEmpty(wordReflection.defaultReflection)){
                reflectionsRelatedToWords.push(wordReflection.defaultReflection)
            }
            reflectionsRelatedToWords = reflectionsRelatedToWords.concat(wordReflection.reflections);
            var uniqueReflections = getUnique(reflectionsRelatedToWords,result.concat(existingReflections));
            var reflectionThumbnails = _.sample(self.createReflectionThumbnails(uniqueReflections),3);
            result = result.concat(reflectionThumbnails);
            return result;
        },[]);
        return reflections;
    };

    self.getSongsFromWordReflections = function(wordSongs,existingSongs){
        var songs =_.reduce(wordSongs,function(result,wordSong){
            var uniqueSongs = getUnique(wordSong.songs,result.concat(existingSongs));
            var songThumbnails = _.sample(self.createSongThumbnails(uniqueSongs),3);
            result = result.concat(songThumbnails);
            return result;
        },[]);
        return songs;
    };

    return self;
})();