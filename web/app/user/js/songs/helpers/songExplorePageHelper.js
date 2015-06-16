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

    var containsItem = function(existingItems, newItem) {
        return _.some(existingItems, function (existingItem) {
            return existingItem ? newItem.id === existingItem.id : false;
        });
    };

    var getUnique = function(newItems,existingItems ){
        var uniqueItems= newItems || [];
        if(!_.isEmpty(existingItems) && !_.isEmpty(newItems)){
            uniqueItems = _.reject(newItems,function(newItem){
                return containsItem(existingItems, newItem)
            });
        }
        return uniqueItems;
    };

    self.getReflectionsFromWordReflections = function(wordReflections,existingReflections){
        function reflectionsWithoutTheDefault(reflectionsRelatedToWord, wordReflection) {
            return reflectionsRelatedToWord.concat(_.reject(wordReflection.reflections, function (reflection) {
                if (!_.isEmpty(wordReflection.defaultReflection)) {
                    return reflection.id === wordReflection.defaultReflection.id;
                }
                return false;
            }));
        }

        var reflections =_.reduce(wordReflections,function(result,wordReflection){
            var reflectionsRelatedToWord =[];
            reflectionsRelatedToWord = reflectionsWithoutTheDefault(reflectionsRelatedToWord, wordReflection);
            var uniqueReflections = getUnique(reflectionsRelatedToWord,result.concat(existingReflections));
            var reflectionThumbnails = _.sample(self.createReflectionThumbnails(uniqueReflections),3);
            if(!_.isEmpty(wordReflection.defaultReflection) && !containsItem(reflectionThumbnails,wordReflection.defaultReflection) ){
                reflectionThumbnails.unshift(wordReflection.defaultReflection)
            }
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