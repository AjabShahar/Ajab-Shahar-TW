'use strict';

var AjabShahar = AjabShahar || {};
AjabShahar.user = AjabShahar.user || {};
AjabShahar.user.ContentUrlCreator = (function () {
    var self = {};

    self.getUrl = function(entity,type){
        switch (type){
            case 'song': return self.songUrl(entity);
            case 'word':return self.wordUrl(entity);
            case 'reflection':return self.reflectionUrl(entity);
            case 'person':return self.personUrl(entity);
        }
        return "";
    };

    self.personUrl = function(person){
        return "/people/all#"+formattedTitle(getTitleText(person,"person"));
    };

    self.personExploreUrl = function(person){
        return "/people/explore/"+person.id+"/"+formattedTitle(getTitleText(person,"person"));
    };

    self.wordUrl = function(word){
        return "/words/details/"+word.id+"/"+formattedTitle(getTitleText(word,"word"));
    };

    self.songUrl = function(song){
        return "/songs/?id="+song.id+"&title="+formattedTitle(getTitleText(song,"song"));
    };

    self.songExploreUrl = function(song){
        return "/songs/explore/"+song.id+"/"+formattedTitle(getTitleText(song,"song"));
    };

    self.reflectionUrl = function(reflection){
        return "/reflections/details/"+reflection.id+"/"+formattedTitle(getTitleText(reflection,"reflection"));
    };

    var getTitleText = function(entity,type){
        switch (type){
            case 'song':
                if(entity.translitTitle) return entity.translitTitle;
                if(entity.title) return entity.title;
                if(entity.englishTransliteration) return entity.englishTransliteration;
                if(entity.englishTransliterationTitle) return entity.englishTransliterationTitle;
                return entity.songTitle? entity.songTitle.englishTransliteration:  "";
            case 'word':
                if(entity.translitTitle) return entity.translitTitle;
                if(entity.title) return entity.title;
                if(entity.transliteration) return entity.transliteration;
                if(entity.wordTransliteration) return entity.wordTransliteration;
                return "";
            case 'reflection':
                if(entity.translitTitle) return entity.translitTitle;
                if(entity.englishTitle) return entity.englishTitle;
                if(entity.title) return entity.title;
                return "";
            case 'person':
                if(entity.name) return entity.name;
                //if(entity.shareTitle) return entity.shareTitle;
                return "";
        }
        return "";
    };
    var formattedTitle = function(title){
        if(title){
            var formattedTitle = _.compact(title.split(/[^\w]+/));
            if(formattedTitle.length >5 ){
                formattedTitle.splice(5,formattedTitle.length-5);
            }
            return formattedTitle.join("-")
        }
        return "";
    };

    self.getFormattedTitle = function(title){
        return formattedTitle(title);
    };
    return self;
})();