var AjabShahar  = AjabShahar|| {};
AjabShahar.ContentObject = function(contentItem,type){
    var self = this;
    self.actualContent = contentItem;
    var buildFromSong = function(song){
        self.type = type;
        self.id = song.id;
        self.imgSrc = song.thumbnailUrl;
        self.videoSrc = song.videoId;
        self.englishTranslation =song.englishTranslation;
        self.englishTransliteration =song.englishTransliteration;
        self.category = "SONG";
        self.customStyle  = song.customStyle;
        self.whatAndWhoShort = _.isEmpty(song.singer)?null:"sings "+song.singer.toUpperCase();
        self.whatAndWhoLong = _.isEmpty(song.singers)?null:"sing "+song.singers.toUpperCase();
        self.moreInfo = song.poet? "Poet "+song.poet.toUpperCase():null;
        self.duration = song.duration;
    };
    self.getTitle = function(format){
        return (format === 'Transliteration')? self.englishTransliteration : self.englishTranslation;
    };

    self.getSecondTitle = function(format){
        return (format === 'Transliteration')? self.englishTranslation : self.englishTransliteration;
    };

    if(type === 'song'){
        buildFromSong(contentItem);
    } 
    return self;
};