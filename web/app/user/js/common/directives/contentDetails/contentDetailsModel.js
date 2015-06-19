'use strict';

var AjabShahar = AjabShahar || {};

AjabShahar.DetailsObject = function (content, type) {
    var self = this;
    self.type = type;
    self.originalObject = content;

    var pluckPropertyFrom = function (obj, propertyName, lambdaFunctionName, callback) {
        if (!_.isEmpty(obj) && !_.isEmpty(obj[propertyName])) {
            if (lambdaFunctionName) {
                return obj[propertyName][lambdaFunctionName](callback);
            }
            return obj[propertyName]
        }
        return null;
    };

    var getRelatedLinksFromSong = function (song) {
        var relatedLinks = [];
        var WORD_DETAILS_PATH = "/words/#/details/";

        if (!_.isEmpty(song)) {
            var speakerLink = pluckPropertyFrom(song,"singers","map",function(singer){
                return{
                    name: singer.name,
                    description: "SINGER",
                    link:"/people/all#"+singer.id
                }
            });

            var relatedPeople = pluckPropertyFrom(song, "poets", "map", function (poet) {
                return {
                    name: poet.name,
                    description: "POET",
                    link:"/people/all#"+poet.id
                };
            });

            var relatedWords = pluckPropertyFrom(song, "words", "map", function (word) {
                if (word.rootWord && word.publish) {
                    return {
                        name: word.wordTransliteration,
                        link: WORD_DETAILS_PATH + word.id,
                        alternateName: word.wordTranslation,
                        description: "WORD"
                    }
                }
            });

            relatedLinks = relatedLinks.concat(speakerLink).concat(relatedPeople).concat(relatedWords);
        }
        return relatedLinks;

    };

    var getRelatedLinksFromWord = function (word) {
        return pluckPropertyFrom(word, "writers", "map", function (writer) {
            return {
                name: writer.name,
                description: writer.primaryOccupation,
                link:"/people/all#"+writer.id
            };
        });
    };

    var getRelatedLinksFromReflection = function (reflection) {
        var relatedLinks = [];
        var WORD_DETAILS_PATH = "/words/#/details/";
        var SONG_DETAILS_PATH = "/songs/?id=";

        if (!_.isEmpty(reflection)) {
            var speakerLink = {
                name: reflection.speaker ? reflection.speaker.name : "",
                description: reflection.speaker ? reflection.speaker.primaryOccupation : "",
                link:reflection.speaker ? "/people/all#"+reflection.speaker.id : ""
            };

            var relatedPeople = pluckPropertyFrom(reflection, "people", "map", function (person) {
                return {
                    name: person.name,
                    description: person.primaryOccupation,
                    link:"/people/all#"+person.id
                };
            });

            var relatedWords = pluckPropertyFrom(reflection, "words", "map", function (word) {
                if (word.rootWord && word.publish) {
                    return {
                        name: word.wordTransliteration,
                        link: WORD_DETAILS_PATH + word.id,
                        alternateName: word.wordTranslation,
                        description: "WORD"
                    }
                }
            });

            var relatedSongs = pluckPropertyFrom(reflection, "songs", "map", function (song) {
                    return {
                        name: song.englishTransliterationTitle,
                        link: SONG_DETAILS_PATH + song.id,
                        description: "SONG"
                    }
            });

            relatedLinks = relatedLinks.concat(speakerLink).concat(relatedPeople).concat(relatedWords).concat(relatedSongs);
        }
        return relatedLinks;
    };

    var getPeopleFromWord = function (word) {
        return pluckPropertyFrom(word, "writers", "map", function (writer) {
            return writer.name
        });
    };

    self.getContentFormat = function () {
        if (self.audioId) {
            return 'audio';
        }
        if (self.videoId) {
            return 'video';
        }
        if (!_.isEmpty(self.textSections)) {
            return 'text';
        }
    };

    var buildFromSong = function (song) {
        self.id = song.id;
        self.audioId = song.soundCloudTrackId;
        self.videoId = song.youtubeVideoId;
        self.downloadUrl = song.downloadURL;
        self.image = song.thumbnailURL;
        self.about = song.about;
        self.links = getRelatedLinksFromSong(song);
        self.shareUrl = "/songs/?id=" + self.id;
        self.shareTitle = song.songTitle.englishTransliteration + " \n";
    };

    var buildFromWord = function (word) {
        var getFromWord = function (word, type) {
            if (type === 'audio') {
                if (!_.isEmpty(word.defaultReflection)) {
                    return word.defaultReflection.soundCloudId;
                }
            }
            if (type === 'video') {
                if (!_.isEmpty(word.defaultReflection)) {
                    return word.defaultReflection.youtubeVideoId;
                }
            }
            if (type === 'text') {
                if (!_.isEmpty(word.wordIntroductions)) {
                    return word.wordIntroductions[0].wordIntroEnglish;
                }
            }
            return null;
        };

        self.id = word.id;
        self.audioId = getFromWord(word, 'audio');
        self.videoId = getFromWord(word, 'video');
        self.textSections = getFromWord(word, 'text');
        //self.about = getAboutFromWord(word);
        self.links = getRelatedLinksFromWord(word);
        self.verb = "Introduction by";
        self.people = getPeopleFromWord(word);
        self.displayAjabShaharTeam = word.displayAjabShaharTeam;
        self.info = "";
        self.image = word.thumbnailUrl;
        self.shareUrl = "/words/details/" + self.id;
        self.shareTitle = word.wordTransliteration + " \n";
    };

    var buildFromReflection = function (reflection) {
        var getReflectionTranscripts = function (reflection) {
            if (!_.isEmpty(reflection.reflectionTranscripts)) {
                return reflection.reflectionTranscripts[0].englishTranscript;
            }
            return null;
        };
        self.id = reflection.id;
        self.audioId = reflection.soundCloudId;
        self.videoId = reflection.youtubeVideoId;
        self.textSections = getReflectionTranscripts(reflection);
        self.links = getRelatedLinksFromReflection(reflection);
        self.verb = reflection.verb;
        self.people = (reflection.speaker) ? [reflection.speaker.name] : [];
        self.title = reflection.title;
        self.info = reflection.info;
        self.about = reflection.about;
        self.excerpt = reflection.reflectionExcerpt;
        self.image = reflection.thumbnailURL;
        self.shareUrl = "/reflections/details/" + self.id;
        self.shareTitle = reflection.title + " \n";
    };

    if (type === 'song') {
        buildFromSong(content);
    }
    else if (type === 'word') {
        buildFromWord(content);
    }
    else if (type === 'reflection') {
        buildFromReflection(content);
    }
    return self;
};
