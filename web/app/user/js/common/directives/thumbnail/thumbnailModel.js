var AjabShahar  = AjabShahar|| {};
AjabShahar.ThumbnailObject = function(contentItem,type){
    var self = this;
    self.actualContent = contentItem;
    var buildFromSong = function(song){
        /*
        self.type = type;
        self.id = song.id;
        self.imgSrc = song.thumbnailUrl;
        self.videoSrc = song.videoId;
        self.audioSrc= song.audioId;
        self.englishTranslation =song.englishTranslation;
        self.englishTransliteration =song.englishTransliteration;
        self.category = "SONG";
        self.verb=getVerbForSong(song);
        self.moreInfo = song.poet? "Poet "+song.poet.toUpperCase():null;
        self.duration = song.duration;
        */
    };

    var buildFromWord = function(word){
        if(!_.isEmpty(word)) {
            self.type = type;
            self.id = word.id;
            self.thumbnailImg = 'https://yt3.ggpht.com/-JtqzFmOGDiI/AAAAAAAAAAI/AAAAAAAAAAA/McQLKmfBpqg/s900-c-k-no/photo.jpg';
            self.description = word.englishIntroExcerpt;
            self.verbPeople = {
                verb: "Intro by",
                people: getWritersForWord(word)
            };
            self.englishTitle = word.wordTranslation;
            self.translitTitle = word.wordTransliteration;
            self.contentFormat = "text";
        }
    };

    var buildFromReflection = function(reflection){
        if(!_.isEmpty(reflection)){
            self.type = type;
            self.id = reflection.id;
            self.thumbnailImg = "/user/img/reflections/bw_background2b.jpg";
            self.description =reflection.about;
            self.verbPeople ={
                verb:reflection.verb,
                people:reflection.speaker?reflection.speaker.name:""
            };
            self.englishTitle =reflection.title;
        }
    };

    var getWritersForWord = function(word){
        if(!_.isEmpty(word.writers)){
            return word.writers[0].name +  (word.displayAjabShaharTeam === 'true' || word.displayAjabShaharTeam ? ", Ajab Shahar Team":"");
        }
        return "";
    };

    var getVerbForSong = function(song){
        var verb = _.isEmpty(song.singer)?null:"sings "+song.singer.toUpperCase();
        verb = !verb && _.isEmpty(song.singers)?null:"sing "+song.singers.toUpperCase();
        return verb;
    };

    self.getTitle = function(contentTextRepresentation){
        var title = (contentTextRepresentation === 'Transliteration') ? self.translitTitle : self.englishTitle;
        return self.type === 'reflection'? self.englishTitle: title;
    };

    self.getSecondTitle = function(contentTextRepresentation){
        var title = (contentTextRepresentation === 'Translation') ? self.englishTitle : self.translitTitle;
        return self.type === 'reflection'? "": title;
    };

    self.showPrimaryVerbPeopleAlways = function(){
        return !(self.type === 'song' && self.verbPeople.verb === 'sing') && self.type !== 'word';
    };

    self.showPrimaryVerbPeopleInDetails = function(){
        return (self.type === 'song' && self.verbPeople.verb === 'sing')
    };

    if(type === 'song'){
        buildFromSong(contentItem);
    }
    else if(type === 'word'){
        buildFromWord(contentItem);
    }
    else if(type === 'reflection'){
        buildFromReflection(contentItem);
    }
    return self;
};