'use strict';

var AjabShahar  = AjabShahar|| {};


AjabShahar.DetailsObject = function(content,type){
    var self = this;
    self.type = type;
    self.originalObject = content;
    var WORD_DETAILS_PATH ="/user/js/words/#/details/";
    var SONG_DETAILS_PATH ="/user/partials/songs/details.html?id=";


    var pluckPropertyFrom = function(obj,propertyName,lambdaFunctionName,callback){
        if(!_.isEmpty(obj) && !_.isEmpty(obj[propertyName])){
            if(lambdaFunctionName){
                return obj[propertyName][lambdaFunctionName](callback);
            }
            return obj[propertyName]
        }
        return null;
    };

    var getRelatedLinksFromSong = function (song) {

    };

    var getRelatedLinksFromWord = function (word) {
        return pluckPropertyFrom(word,"writers","map",function(writer){
            return {
                name :writer.name,
                description:writer.primaryOccupation
            };
        });
    };

    var getRelatedLinksFromReflection = function (reflection) {
        var relatedLinks = [];

        if(!_.isEmpty(reflection)){
            var speakerLink = {
                name :reflection.speaker? reflection.speaker.name:"",
                description:reflection.speaker? reflection.speaker.primaryOccupation:""
            };

            var relatedPeople = pluckPropertyFrom(reflection,"people","map",function(person){
                return {
                    name: person.name,
                    description: person.primaryOccupation
                };
            });

            var relatedWords = pluckPropertyFrom(reflection,"words","map",function(word){
                return {
                    name:word.wordTransliteration,
                    link:WORD_DETAILS_PATH+word.id,
                    alternateName: word.wordTranslation,
                    description:"WORD"
                }
            });

            var relatedSongs = pluckPropertyFrom(reflection,"songs","map",function(song){
                return {
                    name : song.englishTransliterationTitle,
                    link: SONG_DETAILS_PATH+song.id,
                    description:"SONG"
                }
            });

            relatedLinks = relatedLinks.concat(speakerLink).concat(relatedPeople).concat(relatedWords).concat(relatedSongs);
        }
        return relatedLinks;
    };

    var getPeopleFromWord = function (word) {
        return pluckPropertyFrom(word,"writers","map",function(writer){ return writer.name});
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
        self.verb="Introduction by";
        self.people = getPeopleFromWord(word);
        self.displayAjabShaharTeam = word.displayAjabShaharTeam;
    };

    var buildFromReflection = function(reflection){
        var getReflectionTranscripts = function(reflection){
            if(!_.isEmpty(reflection.reflectionTranscripts)){
                return reflection.reflectionTranscripts.map(function(transcript){
                    return transcript.englishTranscript;
                });
            }
            return null;
        };
        self.id = reflection.id;
        self.audioId = reflection.soundCloudId;
        self.videoId = reflection.youtubeVideoId;
        self.textSections = getReflectionTranscripts(reflection);
        self.links = getRelatedLinksFromReflection(reflection);
        self.verb = reflection.verb;
        self.people = [reflection.speaker.name];
        self.title = reflection.title;
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
