var AjabShahar = AjabShahar || {};

AjabShahar.ThumbnailObject = function (contentItem, type) {
    var self = this;
    self.actualContent = contentItem;
    var buildFromSong = function (song) {
        if (!_.isEmpty(song)) {
            self.type = type;
            self.id = song.id;
            self.verbPeople = {
                verb: getVerbForSong(song),
                people: getSingersFromSong(song.singers)
            };
            self.secondaryVerbPeople = {
                verb: !_.isEmpty(song.poets) ? "POET" : undefined,
                people: !_.isEmpty(song.poets) ? song.poets[0].name : ""
            };
            self.duration = song.duration;
            self.contentCategory = song.songCategory ? song.songCategory.name : "song";
            if (!_.isUndefined(song.songTitle)) {
                self.thumbnailImg = song.thumbnailURL;
                self.englishTitle = song.songTitle.englishTranslation;
                self.translitTitle = song.songTitle.englishTransliteration;
                self.contentFormat = song.youtubeVideoId ? 'video' : 'audio';
            }
            else {
                self.englishTitle = song.englishTranslationTitle;
                self.translitTitle = song.englishTransliterationTitle;
                self.thumbnailImg = song.thumbnailUrl;
                self.contentFormat = song.contentFormat;
            }
        }
    };

    var buildFromWord = function (word) {
        if (!_.isEmpty(word)) {
            self.type = type;
            self.id = word.id;
            self.thumbnailImg = word.thumbnailUrl ? word.thumbnailUrl : 'https://yt3.ggpht.com/-JtqzFmOGDiI/AAAAAAAAAAI/AAAAAAAAAAA/McQLKmfBpqg/s900-c-k-no/photo.jpg';
            self.description = word.englishIntroExcerpt;
            self.verbPeople = {
                verb: "Intro by",
                people: getWritersForWord(word)
            };
            self.englishTitle = word.wordTranslation;
            self.translitTitle = word.wordTransliteration;
            self.contentFormat = "text";
            self.contentCategory = "word";
        }
    };

    var buildFromReflection = function (reflection) {
        if (!_.isEmpty(reflection)) {
            self.type = type;
            self.id = reflection.id;
            self.thumbnailImg = reflection.thumbnailURL ? reflection.thumbnailURL : "/user/img/reflections/bw_background2b.jpg";
            self.description = reflection.excerpt ? reflection.excerpt : reflection.reflectionExcerpt;
            self.verbPeople = {
                verb: reflection.verb,
                people: reflection.speaker ? reflection.speaker.name : ""
            };
            self.englishTitle = reflection.title;
            self.contentCategory = "reflection";
            self.duration = reflection.duration;
            self.contentFormat = getReflectionFormat(reflection);
        }
    };

    var getReflectionFormat = function (reflection) {
        if (reflection.contentType && !_.isEmpty(reflection.contentType))
            return reflection.contentType;
        else if (reflection.youtubeVideoId && !_.isEmpty(reflection.youtubeVideoId))
            return "video";
        else if (reflection.soundCloudId && !_.isEmpty(reflection.soundCloudId))
            return "audio";
        else if (!_.isEmpty(reflection.reflectionTranscripts))
            return "text";
    };

    var getWritersForWord = function (word) {
        if (!_.isEmpty(word.writers)) {
            return word.writers[0].name + (word.displayAjabShaharTeam === 'true' || word.displayAjabShaharTeam ? ", Ajab Shahar Team" : "");
        }
        return "";
    };

    var getVerbForSong = function (song) {
        if (!_.isEmpty(song.singers)) {
            return song.singers.length > 1 ? "SING" : "SINGS";
        }
        return "";
    };

    var getSingersFromSong = function (singers) {
        var value = "";
        if (!_.isEmpty(singers)) {
            value = "" + singers[0].name;
            for (var index = 1; index < singers.length; index++) {
                if (index == singers.length - 1)
                    value += ' & ' + singers[index].name;
                else
                    value += ', ' + singers[index].name;
            }
        }
        return value;
    };

    self.getTitle = function (contentTextRepresentation) {
        if (!contentTextRepresentation && self.type !== 'reflection') return self.translitTitle;

        var title = (contentTextRepresentation === 'Transliteration' || contentTextRepresentation === 'transliteration') ? self.translitTitle : self.englishTitle;
        return self.type === 'reflection' ? self.englishTitle : title;
    };

    self.getSecondTitle = function (contentTextRepresentation) {
        if (!contentTextRepresentation && self.type !== 'reflection') return self.englishTitle;

        var title = (contentTextRepresentation === 'Transliteration' || contentTextRepresentation === 'transliteration') ? self.englishTitle : self.translitTitle;
        return self.type === 'reflection' ? "" : title;
    };

    self.showPrimaryVerbPeopleAlways = function () {
        return !(self.type === 'song' && self.verbPeople.verb.toLowerCase() === 'sing') || self.type === 'word';
    };

    self.showPrimaryVerbPeopleInDetails = function () {
        return (self.type === 'song' && self.verbPeople.verb.toLowerCase() === 'sing')
    };

    if (type === 'song') {
        buildFromSong(contentItem);
    }
    else if (type === 'word') {
        buildFromWord(contentItem);
    }
    else if (type === 'reflection') {
        buildFromReflection(contentItem);
    }
    return self;
};