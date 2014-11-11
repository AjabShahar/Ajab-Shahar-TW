coupletThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<couplet-with-details overlay-id="'+id +'" custom-style="'+customStyle+'"' +
        ' title="'+details.englishTranslation+'"'+
        ' english-translation-text="'+details.englishTranslationText+'"'+
        ' category-name="'+details.category.name+'"'+
        ' img-src="'+details.thumbnail_url+'"'+
        '</couplet-with-details>';
    }

return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('coupletThumbnailService',[coupletThumbnailService]);