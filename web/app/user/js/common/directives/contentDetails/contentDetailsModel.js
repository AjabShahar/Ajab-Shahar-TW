'use strict';

var AjabShahar  = AjabShahar|| {};

AjabShahar.DetailsObject = function(content,type){
    var self = this;
    self.type = type;
    self.originalObject = content;

    var getRelatedLinksFromSong = function (song) {

    };

    var getRelatedLinksFromWord = function (word) {

    };

    var getRelatedLinksFromReflection = function (reflection) {

    };

    self.getContentFormat = function(){
        if(self.audioId){
            return 'audio';
        }
        if(self.videoId){
            return 'video';
        }
        if(!_.isEmpty(self.textSections)){
            return 'text';
        }
    };

    var buildFromSong = function(song){
        self.id = song.id;
        self.audioId = song.soundCloudTrackId;
        self.videoId = song.youTubeVideoId;
        self.downloadUrl = song.downloadUrl;
        self.about = song.about;
        self.links = getRelatedLinksFromSong(song);
    };

    var buildFromWord = function(word){
        var getFromWord = function (word,type) {
            if(type === 'audio'){
                if(!_.isEmpty(word.defaultReflection)){
                    return word.defaultReflection.soundCloudId;
                }
            }
            if(type === 'video'){
                if(!_.isEmpty(word.defaultReflection)){
                    return word.defaultReflection.youtubeVideoId;
                }
            }
            if(type ==='text'){
                if(!_.isEmpty(word.wordIntroductions)){
                    return word.wordIntroductions.map(function(wordIntro){
                        return wordIntro.wordIntroEnglish;
                    });
                }
            }
            return null;
        };

        self.id = word.id;
        self.audioId = getFromWord(word,'audio');
        self.videoId = getFromWord(word,'video');
        self.textSections = getFromWord(word,'text');
        //self.about = getAboutFromWord(word);
        self.links = getRelatedLinksFromWord(word);
    };

    var buildFromReflection = function(reflection){
        self.id = reflection.id;
        self.audioId = reflection.soundCloudId;
        self.videoId = reflection.youtubeVideoId;
        self.textSections = reflection.reflectionTranscripts;
        self.links = getRelatedLinksFromReflection(reflection);
    };

    if(type === 'song'){
        buildFromSong(content);
    }
    else if(type === 'word'){
        buildFromWord(content);
    }
    else if(type === 'reflection'){
        buildFromReflection(content);
    }
    return self;
};
